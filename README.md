# Overview

<div style="text-align: center">

[![Java CI with Gradle](https://github.com/astrapi69/gson-extensions/actions/workflows/gradle.yml/badge.svg)](https://github.com/astrapi69/gson-extensions/actions/workflows/gradle.yml)
[![Coverage Status](https://codecov.io/gh/astrapi69/gson-extensions/branch/develop/graph/badge.svg)](https://codecov.io/gh/astrapi69/gson-extensions)
[![Open Issues](https://img.shields.io/github/issues/astrapi69/gson-extensions.svg?style=flat)](https://github.com/astrapi69/gson-extensions/issues)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/gson-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/gson-extensions)
[![Javadocs](http://www.javadoc.io/badge/io.github.astrapi69/gson-extensions.svg)](http://www.javadoc.io/doc/io.github.astrapi69/gson-extensions)
[![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT)
[![Donate](https://img.shields.io/badge/donate-❤-ff2244.svg)](https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=GVBTWLRAZ7HB8)
[![Hits Of Code](https://hitsofcode.com/github/astrapi69/gson-extensions?branch=develop)](https://hitsofcode.com/github/astrapi69/gson-extensions/view?branch=develop)
[![Lines Of Code](https://tokei.rs/b1/github/astrapi69/gson-extensions)](https://github.com/astrapi69/gson-extensions)

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
version: [![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/gson-extensions/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.astrapi69/gson-extensions)

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

Maven dependency is now on sonatype.
Check out [sonatype repository](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~gson-extensions~~~) for latest snapshots and releases.

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

# Donations

This project is kept as an open source product and relies on contributions to remain being
developed. If you like this library, please consider a donation

over paypal: <br><br>
<a href="https://www.paypal.com/cgi-bin/webscr?cmd=_s-xclick&hosted_button_id=MJ7V43GU2H386" target="_blank">
<img src="https://www.paypalobjects.com/en_US/GB/i/btn/btn_donateCC_LG.gif" alt="PayPal this" title="PayPal – The safer, easier way to pay online!" style="border: none" />
</a>
<br><br>
or over bitcoin(BTC) with this address:

bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/bc1ql2y99q7e8psndhcc3gferk03esw3qqf677rhjy.png"
alt="Donation Bitcoin Wallet" width="250"/>

or over FIO with this address:

FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/FIO7tFMUVAA9cHiPPqKMfMXiSxHrbpiFyRYqTketNuM67aULuwjop.png"
alt="Donation FIO Wallet" width="250"/>

or over Ethereum(ETH) with:

0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xc057D159D3C8f3311E73568b334FF6fE82EB2b7D.png"
alt="Donation Ethereum Wallet" width="250"/>

or over Ethereum Classic(ETC) with:

0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/0xF708cA86D86C246B69c3F4BAe431eBbe0c2bfddD.png"
alt="Donation Ethereum Classic Wallet" width="250"/>

or over Dogecoin(DOGE) with:

D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/D5yi4Um8cpakd6yPRm2hGWuQ5nrVzhSSW1.png"
alt="Donation Dogecoin Wallet" width="250"/>

or over Monero(XMR) with:

49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw

<img src="https://github.com/astrapi69/jgeohash/blob/master/src/main/resources/img/49bqeRQ7Bf49oJFVC72pqpe5hFbb62pfXDYPdLsadGGF81KZW2ZfrPZ8PbAVu5X2v1TYAspeczMya3cYQysNS4usRRPQHVw.png"
alt="Donation Monero Wallet" width="250"/>

or over flattr:

<a href="https://flattr.com/submit/auto?fid=r7vp62&url=https%3A%2F%2Fgithub.com%2Fastrapi69%2Fgson-extensions" target="_blank">
<img src="http://api.flattr.com/button/flattr-badge-large.png" alt="Flattr this" title="Flattr this" style="border: none" />
</a>

## Semantic Versioning

The versions of gson-extensions are maintained with the Semantic Versioning guidelines.

Release version numbers will be incremented in the following format:

`<major>.<minor>.<patch>`

For detailed information on versioning you can visit the [wiki page](https://github.com/lightblueseas/mvn-parent-projects/wiki/Semantic-Versioning).

## Want to Help and improve it? ###

The source code for gson-extensions are on GitHub. Please feel free to fork and send pull requests!

Create your own fork of [astrapi69/gson-extensions/fork](https://github.com/astrapi69/gson-extensions/fork)

To share your changes, [submit a pull request](https://github.com/astrapi69/gson-extensions/pull/new/develop).

Don't forget to add new units tests on your changes.

## Contacting the Developers

Do not hesitate to contact the gson-extensions developers with your questions, concerns, comments, bug reports, or feature requests.
- Feature requests, questions and bug reports can be reported at the [issues page](https://github.com/astrapi69/gson-extensions/issues).

## Credits

|**Nexus Sonatype repositories**|
|     :---:      |
|[![sonatype repository](https://img.shields.io/nexus/r/https/oss.sonatype.org/io.github.astrapi69/gson-extensions.svg?style=for-the-badge)](https://oss.sonatype.org/index.html#nexus-search;gav~io.github.astrapi69~gson-extensions~~~)|
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
