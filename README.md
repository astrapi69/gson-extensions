# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/gson-extensions/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/gson-extensions/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/gson-extensions/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/gson-extensions)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/gson-extensions.svg?style=flat)](https://github.com/astrapi69/gson-extensions/issues)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/gson-extensions)](https://central.sonatype.com/artifact/io.github.astrapi69/gson-extensions)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/gson-extensions.svg)](http://www.javadoc.io/doc/io.github.astrapi69/gson-extensions)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/gson-extensions?branch=develop)](https://hitsofcode.com/github/astrapi69/gson-extensions/view?branch=develop)

</div>

Useful extensions methods and utilities for transform json string to java object and back to json string with gson library.
You can find the official documentation from gson [here](https://github.com/google/gson/blob/master/UserGuide.md)

# Support this project

> Please support this project by simply putting a Github <!-- Place this tag where you want the button to render. -->
<a class="github-button" href="https://github.com/astrapi69/gson-extensions" data-icon="octicon-star" aria-label="Star astrapi69/gson-extensions on GitHub">Star ⭐</a>
>
> Share this library with friends on Twitter and everywhere else you can
>
> If you love this project [![donation](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
>
> or for more donation options go the [donations section](#Donations)

## Note

No animals were harmed in the making of this library.

## License

The source code comes under the liberal MIT License, making gson-extensions great for all types of applications.

## gradle dependency

Replace the variable ${latestVersion} with the current latest
version: [![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/gson-extensions)](https://central.sonatype.com/artifact/io.github.astrapi69/gson-extensions)

You can first define the version in the ext section and add than the following gradle dependency
to your project `build.gradle` if you want to import the core functionality of gson-extensions:

define version in file gradle.properties
```
gsonExtensionsVersion=${latestVersion}
```

or in build.gradle ext area

```
    gsonExtensionsVersion = "${latestVersion}"
```

and than add the dependency to the dependencies area

```
    implementation("io.github.astrapi69:gson-extensions:$gsonExtensionsVersion")
```

# with new libs.versions.toml file

If you use the new libs.versions.toml file for new automatic catalog versions update

```
[versions]
gson-extensions-version=${latestVersion}

[libraries]
gson-extensions = { module = "io.github.astrapi69:gson-extensions", version.ref = "gson-extensions-version" }
```
then add the dependency to the dependencies area

```
    implementation libs.gson.extensions
```


## Maven dependency

The library is on Maven Central. Check out [Maven Central](https://central.sonatype.com/artifact/io.github.astrapi69/gson-extensions) for the latest release.

Add the following maven dependency to your project `pom.xml` if you want to import the core functionality of gson-extensions:

Than you can add the dependency to your dependencies:

    <properties>
            ...
        <!-- gson-extensions version -->
        <gson-extensions.version>3</gson-extensions.version>
            ...
    </properties>
            ...
        <dependencies>
            ...
            <!-- gson-extensions DEPENDENCY -->
            <dependency>
                <groupId>io.github.astrapi69</groupId>
                <artifactId>gson-extensions</artifactId>
                <version>${gson-extensions.version}</version>
            </dependency>
            ...
        </dependencies>

## Support this project

If gson-extensions is useful to you, a star on [GitHub](https://github.com/astrapi69/gson-extensions) helps others find it.
Sharing it or reporting a bug helps just as much.

If you would like to contribute financially, the channels are on the repository's Sponsor button, which
reads [.github/FUNDING.yml](.github/FUNDING.yml): GitHub Sponsors, Liberapay, Ko-fi and PayPal.

## Semantic Versioning

The versions of gson-extensions are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Releasing

What a release consists of, in the order it is run:

1. **One pull request** with the CHANGELOG entry and `projectVersion` without `-SNAPSHOT`
   (`docs: changelog for X.Y, and the version that carries it`), the full build green on it, the
   release gate on its tree (the tests, javadoc without a warning, class files of Java 25, no
   `-SNAPSHOT` in the resolved dependencies, a build against an empty local Maven repository with
   `-Dmaven.repo.local=<an empty directory>`), and `scripts/check-reproducible.sh` on the JDK
   `.sdkmanrc` names reporting the five artifacts identical.
2. **Tag the merge commit** with `./gradlew tagRelease`, which names it `RELEASE-X.Y`, and push the
   tag. `publish.yml` uploads to the Central Portal, where the maintainer releases it. A pushed tag is
   never moved or deleted; a failed run is fixed on the branch and re-run.
3. **Verify on Central**: the jar, pom, `.module`, `-sources.jar` and `-javadoc.jar`, their sha1, and
   `gpg --verify` on the `.asc`.
4. **GitHub release** from the CHANGELOG entry.
5. **`master` follows the release** by a merge of the tag: on `master`,
   `git merge --no-ff "RELEASE-X.Y^{commit}" -m "Merge tag RELEASE-X.Y into master"`. Before
   `master` is pushed, `git diff RELEASE-X.Y master` has to be empty; if it is not, nothing is
   pushed. Never a force push: `master` is always the latest release, nothing else.
6. **Open the next cycle**: `projectVersion` to the next `-SNAPSHOT`.
7. **Bump drafts in the consumers** (RL-127): in every repository of the family that declares
   gson-extensions, a draft pull request that raises it to the release and shows the build before and
   after it, so that a break shows the day the release appears; each is merged by RL-065, a runtime
   major in a session of its own.

## Want to Help and improve it? ###

The source code for gson-extensions are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/gson-extensions/fork](https://github.com/astrapi69/gson-extensions/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/gson-extensions/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the gson-extensions developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/gson-extensions/issues).

## Credits

|**Maven Central**|
|     :---:      |
|[![Maven Central](https://img.shields.io/maven-central/v/io.github.astrapi69/gson-extensions?style=for-the-badge)](https://central.sonatype.com/artifact/io.github.astrapi69/gson-extensions)|
|Special thanks to [sonatype repository](https://www.sonatype.com) for providing a free maven repository service for open source projects|
|     <img width=1000/>     |

|**codecov.io**|
|     :---:      |
|[![Coverage Status](https://codecov.io/gh/astrapi69/gson-extensions/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/gson-extensions)|
|Special thanks to [codecov.io](https://codecov.io) for providing a free code coverage for open source projects|
|     <img width=1000/>     |

|**javadoc.io**|
|     :---:      |
|[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/gson-extensions.svg)](http://www.javadoc.io/doc/io.github.astrapi69/gson-extensions)|
|Special thanks to [javadoc.io](http://www.javadoc.io) for providing a free javadoc documentation for open source projects|
|     <img width=1000/>     |
