#!/usr/bin/env bash
#
# Builds the five artifacts this project publishes twice from the checked-out tree - each time from a clean
# build directory into a fresh local Maven repository - and compares them by SHA-256. A release can be rebuilt
# bit for bit from its tag only if two builds of the same commit give the same bytes. The second build runs in a
# deliberately different environment - another language and region, another time zone, another directory -
# because two builds in the same environment miss what the environment writes into the bytes, as the machine's
# locale did into the javadoc jar of mystic-crypt 13.10 (astrapi69/mystic-crypt#304; RL-094). Adapted from
# scripts/check-reproducible.sh of astrapi69/file-worker and astrapi69/silly-collection.
#
# Usage:
#   scripts/check-reproducible.sh                     both builds on the toolchain Gradle finds
#   scripts/check-reproducible.sh <jdk>               both builds on that JDK only (the one .sdkmanrc names for a
#                                                     release)
#   scripts/check-reproducible.sh <jdk> <other jdk>   the second build on the other JDK, to show that the JDK's
#                                                     patch level does not reach the artifacts
#
# Build 1 runs in this tree under de_DE.UTF-8 and Europe/Berlin, build 2 in a copy of the tree under a
# temporary path, under en_US.UTF-8 and Pacific/Auckland. Both run --no-daemon, so that the build's JVM carries
# the build's own locale and time zone, and without the build cache, so that nothing is reproduced by reuse. A
# locale this machine does not provide is an error, not a silent fallback into sameness.
#
# Exit codes: 0 all five identical; 1 an artifact differs (the archive entries that differ are listed);
# 2 a build failed, a locale is missing, a build did not give five artifacts or the arguments are wrong.
set -uo pipefail

[[ $# -le 2 ]] || { echo "usage: scripts/check-reproducible.sh [<jdk home> [<jdk home of the second build>]]" >&2; exit 2; }
cd "$(dirname "$0")/.."
locale1=de_DE.UTF-8 tz1=Europe/Berlin
locale2=en_US.UTF-8 tz2=Pacific/Auckland
for l in "$locale1" "$locale2"; do
	locale -a 2>/dev/null | grep -qixE "${l%%.*}\.utf-?8" \
		|| { echo "locale $l is not generated on this machine, so the two builds would not differ in it" >&2; exit 2; }
done
work="$(mktemp -d "${TMPDIR:-/tmp}/check-reproducible.XXXXXX")"
trap 'rm -rf "$work"' EXIT
# The signatures are not compared, and signing needs the key: on a machine with signing.gnupg.keyName the gpg
# command could ask for its passphrase. The two builds therefore sign nothing (as astrapi69/silly-collection#99).
printf 'allprojects { tasks.withType(Sign).configureEach { enabled = false } }\n' > "$work/no-signing.gradle"
# The other directory: a copy of the tree without its build output, its Gradle project cache and its git data
mkdir "$work/tree2"
tar -C . --exclude=./build --exclude=./.gradle --exclude=./.git -cf - . | tar -C "$work/tree2" -xf - \
	|| { echo "the copy of the tree failed" >&2; exit 2; }

build()
{
	local n="$1" dir="$2" lang="$3" tz="$4" jdk="${5:-}" args=()
	if [[ -n "$jdk" ]]; then
		[[ -x "$jdk/bin/javac" ]] || { echo "no JDK at $jdk" >&2; exit 2; }
		# Gradle properties, not project properties: Gradle 9 reports -P for these as deprecated and Gradle 10
		# removes it (upgrading guide 9, "toolchain-project-properties")
		args=(-Dorg.gradle.java.installations.auto-detect=false -Dorg.gradle.java.installations.auto-download=false
			"-Dorg.gradle.java.installations.paths=$jdk")
	fi
	echo "build $n in $dir, $lang, $tz${jdk:+, on $jdk}"
	# Gradle prefers the JVM it runs on as the toolchain when it matches, so the build runs on that JDK as well
	(cd "$dir" && env -u LANGUAGE LANG="$lang" LC_ALL="$lang" TZ="$tz" ${jdk:+JAVA_HOME="$jdk"} \
		./gradlew --no-daemon --no-build-cache --console=plain -q --init-script "$work/no-signing.gradle" \
		"-Dmaven.repo.local=$work/repo$n" "${args[@]}" \
		clean publishMavenJavaPublicationToMavenLocal) > "$work/build$n.log" 2>&1 \
		|| { echo "build $n failed:" >&2; tail -20 "$work/build$n.log" >&2; exit 2; }
	find "$work/repo$n" -path '*/io/github/astrapi69/*' \( -name '*.jar' -o -name '*.pom' -o -name '*.module' \) \
		-exec sha256sum {} + | sed "s|$work/repo$n/||" | sort -k2 > "$work/sha$n.txt"
	# a comparison of fewer artifacts than the project publishes proves nothing about the missing ones
	[[ $(wc -l < "$work/sha$n.txt") -eq 5 ]] \
		|| { echo "build $n gave $(wc -l < "$work/sha$n.txt") artifacts, not the five this project publishes" >&2; exit 2; }
}

build 1 . "$locale1" "$tz1" "${1:-}"
build 2 "$work/tree2" "$locale2" "$tz2" "${2:-${1:-}}"

if diff -q "$work/sha1.txt" "$work/sha2.txt" > /dev/null; then
	echo "identical across language, time zone and directory, $(wc -l < "$work/sha1.txt") artifacts:"
	sed 's/^/  /' "$work/sha1.txt"
	exit 0
fi
echo "different:"
join -j 2 "$work/sha1.txt" "$work/sha2.txt" | awk '$2 != $3 {print $1}' | while read -r file; do
	echo "  $file"
	case "$file" in
	*.jar)
		python3 -I - "$work/repo1/$file" "$work/repo2/$file" <<'PY'
import sys, zipfile
a, b = zipfile.ZipFile(sys.argv[1]), zipfile.ZipFile(sys.argv[2])
names = [i.filename for i in a.infolist()]
if names != [i.filename for i in b.infolist()]:
    print('    the entries or their order differ')
for name in names:
    if name in b.namelist() and a.read(name) != b.read(name):
        print('    entry ' + name)
PY
		;;
	esac
done
exit 1
