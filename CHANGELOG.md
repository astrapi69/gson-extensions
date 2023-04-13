## Change log
----------------------

Version 2.2-SNAPSHOT
-------------

ADDED:

- new algorithm for process json arrays conversion to properties list
- new json serializer for properties objects

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
