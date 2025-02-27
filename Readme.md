Burp suite extension Burp Ai Assistant
=============

Language/[Japanese](Readme-ja.md)

This tool is an extension of PortSwigger product, Burp Suite.
Supports Burp suite Professional.

## Overview

This is a tool to use the AI extension of the Burp suite in the UI.

Please refer to the following URL for more information on the AI of Burp suite.

* https://portswigger.net/burp/documentation/desktop/extensions/creating/creating-ai-extensions


## About the latest version

The main repository (main) may contain code under development.
Please download the stable release version from the following.

* https://github.com/raise-isayan/BurpAiAssistant/releases

Please use the following versions

* Burp suite v2025.2 or above

## How to Use

The Burp Suite Extender can be loaded by following the steps below.

1. Click [add] on the [Extender] tab
2. Click [Select file ...] and select ImageMetaExtract.jar.
3. Click [Next], confirm that no error is occurring, and close the dialog with [Close].

### Ai Assistant Menu

- Load: Load prompt history from file

- Save: Save prompt history to file

### Ai Assistant Tab

#### Prompt List

Displays past prompt history

#### Prompt

Select the prompt type.

- System: Set the AI's behavior or role

- User:  Represent user queries

- Assistant:  Represent AI-generated responses from previous interactions

- [Prompt Send] Button: Send prompt


#### Respose

Presenting the latest AI-generated responses 

## build

```
gradlew release
```

## Runtime environment

.Java
* JRE (JDK) 17 (Open JDK is recommended) (https://openjdk.java.net/)

.Burp suite
* v2025.2 or higher (http://www.portswigger.net/burp/)

## Development environment
* NetBean 24 (https://netbeans.apache.org/)
* Gradle 7.6 (https://gradle.org/)

## Required libraries
Building requires a [BurpExtensionCommons](https://github.com/raise-isayan/BurpExtensionCommons) library.
* BurpExtensionCommons v3.2.5.x
  * https://github.com/raise-isayan/BurpExtensionCommons

## Use Library

* google gson (https://github.com/google/gson)
  * Apache License 2.0
  * https://github.com/google/gson/blob/master/LICENSE

* Universal Chardet for java (https://code.google.com/archive/p/juniversalchardet/)
  * MPL 1.1
  * https://code.google.com/archive/p/juniversalchardet/


Operation is confirmed with the following versions.
* Burp suite v2025.2

## important
This tool developed by my own personal use, PortSwigger company is not related at all. Please do not ask PortSwigger about problems, etc. caused by using this tool.
