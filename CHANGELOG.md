## Change log
----------------------

Version 4.1
-------------

CHANGED:

- the runtime dependency file-worker is 21.1 instead of 19.1 (#32). `ObjectToJsonFileExtensions.toJsonFile` calls
  `StoreFileExtensions.toFile(File, String)`, whose `boolean` result file-worker 21.0 removed: 4.1 is compiled against
  21.1 and calls the method that returns nothing (see FIXED); a failed write throws the declared `IOException`
  (file-worker 20.1). No type of file-worker is in the public API of gson-extensions. On the runtime class path silly-io
  is 5.0 instead of 3.6, silly-strings 10 instead of 9.2, crypt-api 10.3 instead of 9.3, checksum-up 3.2 instead of 3.1,
  commons-lang3 3.21.0 instead of 3.20.0, commons-text 1.15.0 instead of 1.12.0, and commons-collections4 4.6.0 replaces
  the milestone 4.5.0-M2; jobj-compare 12, commons-beanutils 1.11.0, commons-collections 3.2.2 and commons-logging 1.3.5
  are gone. **Note:** 4.1 needs file-worker 21.0 or later at runtime: with file-worker 19 or 20 on the class path,
  `toJsonFile` fails with a `NoSuchMethodError`, the mirror of what 4.0 does with 21. Gradle resolves 21.1 unless a
  build forces an older file-worker; a Maven project takes the version it declares itself, so one that declares
  file-worker 19 or 20 keeps it and fails. A project that calls file-worker itself reads the BREAKING entries of
  file-worker 20.0 and 21.0; file-worker 21.1 also changes behavior under its own Note:
  `StoreFileExtensions.toFile(File, String, String)` and the other methods it names write in the default charset for an
  illegal charset name such as `""`, where 21.0 threw an `IllegalCharsetNameException`
- the runtime dependency silly-collection is 30.0 instead of 28.2 (#31), the version file-worker 21.1 asks for.
  gson-extensions uses its `SortedProperties` (`JsonToPropertiesExtensions`), which 30.0 keeps. **Note:**
  `toProperties(File)`, `prepare(File)` and `toProperties(String)` return a `SortedProperties`, so their callers also
  get what silly-collection 28.3 changed in it (astrapi69/silly-collection#46): `keySet()` is a view backed by the
  properties, where it was a copy, and `propertyNames()` lists the keys of the defaults too and throws a
  `ClassCastException` for a key that is not a `String`. A project that gets silly-collection through gson-extensions
  gets 30.0; one that uses `MapFactory` with `KeyValuePair`, or `SortedList`, `SortedUniqueList` or `UniqueList` itself
  reads the BREAKING entries of silly-collection 29.0 and 30.0
- the javadoc jar is the same in every locale: the javadoc JVM runs in English (`-Duser.language=en -Duser.country=US`,
  `-locale en`), where a build in a German locale wrote `lang="de"` and German text into all 40 HTML pages. A javadoc
  warning fails the build (`-Werror`, `failOnError`, `check` depends on `javadoc`); the 11 warnings of 4.0, public
  default constructors without a comment, are fixed by writing the constructors out, public as before, so the API stays
  as it was (#33). **Note:** the language of the published javadoc does not change: the javadoc published for 4.0 was
  built in CI and is already English, the one of 3.1 was German; the javadoc of 4.1 differs from the one of 4.0 in the
  pages of the eleven classes and in the index
- the jar names the JDK only by its feature release (`Build-Jdk-Spec: 25`) and carries no build time, JDK patch level or
  operating system any more; javac compiles with `--release 25`, so `module-info.class` requires `java.base` 25 instead
  of the patch level it was built on; the build now sets the fixed entry times and order of every archive, which the 4.0
  archives already had as Gradle 9's default; `mavenLocal()` is read only with `-PuseMavenLocal`; `.sdkmanrc` names the
  JDK of the release, Temurin 25.0.5+8, and the three workflows set up exactly that one and check it (#34)
- the six tests that deserialize employees from JSON naming no `subOrdinates` expect employees whose `subOrdinates` is
  null, as Gson leaves it, and compare what Gson returns: two compared the collections only through
  `CollectionExtensions.isEqualCollection`, which up to silly-collection 28.2 called two collections of the same size
  equal when they have no element in common (astrapi69/silly-collection#42); they still call it, now also compare with
  `equals`, and stay green with 28.3; four overwrote the deserialized `subOrdinates` with an empty set before comparing,
  and no longer do. Tests only (#27)
- the dependency graph submission no longer passes OSSRH placeholders (`-PossrhUsername=unused
  -PossrhPassword=unused`): no build script has read them since publishing goes through the Central Portal (#4).
  Workflow only (PR #30)
- build and documentation only: an upload to the Central Portal refuses a half-configured signing key, `GPG_PRIVATE_KEY`
  without `GPG_PASSPHRASE` or the reverse, which signed nothing (#17); a test class that leaves no result fails the
  build, through the Gradle plugin test-results-complete 1.0 (#24, #26); gson-extensions is built with Gradle 9.8.2
  (#26); the README has no cryptocurrency addresses but the family's funding channels (#20), and no dead badges or links
  (#22)

FIXED:

- `ObjectToJsonFileExtensions.toJsonFile` works with file-worker 21: 4.0 was compiled against file-worker 19.1 and
  called `StoreFileExtensions.toFile(File, String)` with the `boolean` result that file-worker 21.0 removed, so with
  file-worker 21 on the class path it failed with a `NoSuchMethodError` when it saved a JSON file, as 3.1 does in
  mystic-crypt-ui's measurement of file-worker 21.1 (astrapi69/mystic-crypt-ui#1030); 4.1 calls the method of 21 that
  returns nothing (#32)

Version 4.0
-------------

BREAKING:

- Java 25 is the minimum, up from 17: the family builds, tests and releases on JDK 25
  (astrapi69/mystic-crypt#234), so this release is 4.0 instead of 3.2 (#12)

CHANGED:

- built with Gradle 9.7.0; the grgit and hierynomus license plugins are gone: a release is tagged
  through git, and spotless writes the license header from `src/main/resources/license-header.txt` (#12)
- releases go to Maven Central through the Central Portal, as in the rest of the family: the
  `com.gradleup.nmcp` plugin uploads, the maintainer releases each deployment in the Portal, and a
  `RELEASE-*` tag runs the new `publish.yml`, which signs with the signing subkey that the secret
  `GPG_KEY_ID` names. The OSSRH staging endpoint this project published to is shut down; its
  settings are gone, among them the switch that held the checksums to md5 and sha1, and snapshots
  resolve from the Central Portal's snapshot repository (#4)
- an upload to the Central Portal leaves only from CI, and a release only from its `RELEASE-*` tag; a local
  build that runs an upload task stops with a message saying so (#10, #14)
- the `gradle/` directory is no longer ignored, so the split build scripts are version controlled (#8)
- dependencies are raised by `versionCatalogUpdate`, stable versions only (#6): file-worker 17.4 -> 19.1,
  which brings commons-beanutils 1.11.0 and closes the high alert on the runtime class path, gson 2.15.0,
  silly-collection 28.2, test-object 9.1, testng 7.12.0, and the plugins spotless 8.10.4, nmcp 1.6.2,
  version-catalog-update 1.1.2, versions 0.66.0. `module-info.java` requires file-worker by
  its new module name, `io.github.astrapisixtynine.file.worker`. crypt-api and crypt-data are gone from
  the test dependencies: no test used them, and their current releases need Java 25

Version 3.1
-------------

ADDED:

- new libs.versions.toml file for new automatic catalog versions update

CHANGED:

- rename of module to new name 'io.github.astrapisixtynine.gson.extensions'
- update of gradle version to 8.10.2
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.51.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.3.0
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new version 7.0.0.BETA2
- update of dependency file-worker to new version 17.4
- update of dependency silly-collection to new minor version 28
- update of test dependency crypt-api to new version 9.3
- update of test dependency crypt-data to new version 9.7
- update of test dependency 'com.github.meanbeanlib:meanbean' to new version 3.0.0-M9
- update of test dependency testng to new version 7.10.2

Version 3
-------------

ADDED:

- new module-info.java file that turns this library to a java module
- new algorithm for process json arrays conversion to properties list
- new json serializer for properties objects
- new class PropertiesToJsonExtensions that transforms java properties object to a json string
- new libs.versions.toml file for new automatic catalog versions update

CHANGED:

- update of JDK to newer version 17
- update of gradle version to 8.4
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.49.0
- update of gradle-plugin dependency 'org.ajoberstar.grgit:grgit-gradle' to new version 5.2.1
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new version 6.22.0
- update of dependency file-worker to new version 17.1
- update of dependency silly-collection to new minor version 27
- update of test dependency test-object to new version 8.2
- update of test dependency crypt-api to new version 8.7
- update of test dependency crypt-data to new version 8.5
- update of test dependency 'com.github.meanbeanlib:meanbean' to new version 3.0.0-M9
- update of test dependency testng to new version 7.8.0

Version 2.1
-------------

CHANGED:

- update of gradle version to 8.0.2
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new version 6.17.0
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.46.0
- update of dependency gson to new version 2.10.1
- update of dependency file-worker to new version 11.6
- update of dependency silly-collection to new minor version 21
- update of test dependency test-object to new version 7.2
- update of test dependency testng to new version 7.7.1
- bugfix of process of arrays in conversion from json to properties

Version 2
-------------

ADDED:

- new factory class GsonBuilderFactory for GsonBuilder

CHANGED:

- update of JDK to newer version 11
- update of gradle version to 7.5.1
- update of gradle plugin dependency com.diffplug.spotless:spotless-plugin-gradle to new version 6.11.0
- update of gradle-plugin dependency com.github.ben-manes.versions.gradle.plugin to new version 0.43.0
- update of dependency silly-collection to new minor version 20.1
- update of test dependency file-worker module version to 11.4
- update of test dependency test-object to new version 7.1
- update of test dependency crypt-api to new version 8.4

Version 1.7
-------------

ADDED:

- new class GenericClassFieldsExclusionStrategy with a class and one set of field names that have to be excluded
- new class GenericMapClassFieldsExclusionStrategy with a map that defines the exclusion

CHANGED:

- update gradle to new version 7.4.1

Version 1.6
-------------

ADDED:

- new generic type adapter class for interfaces created
- new generic json serializer class for interfaces created
- new generic json deserializer class for interfaces created
- new gradle plugin org.ajoberstar.grgit:grgit-gradle in version 4.1.1
- new gradle plugin spotless in version 6.3.0

CHANGED:

- update gradle to new version 7.4
- update gradle-plugin dependency of com.github.ben-manes.versions.gradle.plugin to new version 0.42.0
- update of dependency gson to new version 2.9.0
- update of dependency file-worker to new version 8.2
- update of test dependency test-objects to new version 6
- update of test dependency crypt-api to new version 7.7
- update of test dependency crypt-data to new version 7.11.1
- update of test dependency testng to new version 7.5
- moved test implementation dependency silly-collection to implementation dependency

Version 1.5
-------------

ADDED:

-new class JsonToPropertiesExtensions that transforms a json file or json string to a java properties object

CHANGED:

- update of dependency gson to new version 2.8.9
- update of dependency file-worker to new version 8

Version 1.4
-------------

CHANGED:

- update gradle to new version 7.2
- update of dependency gson to new version 2.8.8
- update of dependency file-worker to new version 5.10
- update of test dependency silly-collections to new version 18
- update of test dependency test-objects to new version 5.5
- update of test dependency crypt-api to new version 7.6.1
- update of test dependency crypt-data to new version 7.8

Version 1.3
-------------

ADDED:

-new class ObjectToJsonFileExtensions for save directly an object to a json file

CHANGED:

- update gradle to new version 7.1
- changed all dependencies from groupid de.alpharogroup to new groupid io.github.astrapi69
- update gradle-plugin dependency of gradle.plugin.com.hierynomus.gradle.plugins:license-gradle-plugin to new version 0.16.1
- changed to new package io.github.astrapi69

Version 1.2
-------------

ADDED:

- new abstract class for the interface ExclusionStrategy created
- new generic class for the interface ExclusionStrategy created

CHANGED:

- update of gradle to new version 6.7
- update of com.github.ben-manes.versions.gradle.plugin to new version 0.36.0
- update of test dependency testng to new version 7.3.0
- update of test dependency silly-collections to new version 8.4

Version 1.1
-------------

ADDED:

- new TypeAdapter class BigDecimalMoneyScaledAdapter for BigDecimal that is indented for money values created
- new methods with gson object argument created

CHANGED:

- removed dependency org.json:json
- code coverage increased to 100%
- extended jar task for build manifest file

Version 1
-------------

ADDED:

- new CHANGELOG.md file created
- moved relevant classes from xml-extensions project

Notable links:
[keep a changelog](http://keepachangelog.com/en/1.0.0/) Don’t let your friends dump git logs into changelogs
