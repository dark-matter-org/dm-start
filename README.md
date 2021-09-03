# dm-start

The dm-start project provides the template of a dark-matter Domain Specific Language (DSL) project.

# Table of Contents

* [1 Environment Setup](#1-environment-setup)
  * [1.1 Requirements](#11-requirements) 
  * [1.2 Preference Tweaks](#12-preference-tweaks) 
* [2 Create your DSL](#2-create-your-dsl)
  * [2.1 Run DSL Bootstrap](21-run-dsl-bootstrap)

# 1 Environment Setup

## Requirements

- JDK 1.8
- Eclipse at [2020-12-R (4.18)](https://www.eclipse.org/downloads/packages/release/2020-12/r) or earlier (the Eclipse IDE for Enterprise Java Developers version is a good choice)

## Preference tweaks

Once Eclipse is installed, it's recommended that you make the following changes to your preferences:

- Version Control (Team) -> Git - Set: Default repository folder to: **${workspace_loc}**
- Maven -> Errors/Warnings - Set: Plugin execution not cover by lifecyle configuration to: **Ignore**

NOTE: When executing the Maven build for your DSL within Eclipse, you may see [warnings](); you may safely ignore them.



# 2 Create your DSL

## Run DSL Bootstrap

Select **Run Configurations -> DSL Bootstrap** and answer the prompts.

Once the DSL has been generated, right click the dm-start project and select "Refresh".

Then, right click and select "Run As -> Maven install" - this will create a shaded, executable jar of your DSL project.




# Appendix: Other Notes

## Maven Warnings

Within Eclipse, you may see a warning like:
```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/Applications/Eclipse-JEE-2020-12-R-dm-start.app/Contents/Eclipse/plugins/org.eclipse.m2e.maven.runtime.slf4j.simple_1.16.0.20200610-1735/jars/slf4j-simple-1.7.5.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [file:/Applications/Eclipse-JEE-2020-12-R-dm-start.app/Contents/Eclipse/configuration/org.eclipse.osgi/5/0/.cp/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: See http://www.slf4j.org/codes.html#multiple_bindings for an explanation.
SLF4J: Actual binding is of type [org.slf4j.impl.SimpleLoggerFactory]
```

You may safely ignore these warnings - but if you want further information on them, see the following Stack Overflow question: [Eclipse Maven: SLF4J: Class path contains multiple SLF4J bindings](https://stackoverflow.com/questions/63518376/eclipse-maven-slf4j-class-path-contains-multiple-slf4j-bindings)

<!--stackedit_data:
eyJoaXN0b3J5IjpbMTg0MDY1NjkwMSw2MjU0OTc0MDcsLTc1Mj
I3MDY5NV19
-->