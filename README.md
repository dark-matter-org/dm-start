# dm-start

The dm-start project provides the template of a dark-matter Domain Specific Language (DSL) project.


# 1 Environment Setup

## 1.1 Requirements

- JDK 1.8
- Eclipse at [2020-12-R (4.18)](https://www.eclipse.org/downloads/packages/release/2020-12/r) or earlier (the Eclipse IDE for Enterprise Java Developers version is a good choice)

## 1.2 Preference tweaks

Once Eclipse is installed, it's recommended that you make the following changes to your preferences:

- Version Control (Team) -> Git - Set: Default repository folder to: **${workspace_loc}**
- Maven -> Errors/Warnings - Set: Plugin execution not covered by lifecyle configuration to: **Ignore**

NOTE: When executing the Maven build for your DSL within Eclipse, you may see [warnings](); you may safely ignore them.



# 2 Create your DSL

## 2.1 Run DSL Bootstrap

In the Eclipse menu bar, select the small black triangle next to the green "Run Configuration" icon and select "DSL Bootstrap":

![run bootstrap](images/run-bootstrap.png)

You will be prompted to enter a new Java package name - in this case, `com.example.xdsl` and an 
abbreviation/file extension for your DSL, in this case, `xdsl`

![run bootstrap](images/enter-package-and-abbrev.png)

After hitting enter after your abbreviation, you'll see feedback on the files being generated:

![run bootstrap](images/code-generated.png)

Once the DSL has been generated, right click the dm-start project and select "Refresh":

![run bootstrap](images/refresh-project.png)

## 2.2 Run Maven Install

Right click the project and select "Run As -> Maven install" - this will create a shaded, executable jar of your DSL project:

![run bootstrap](images/maven-install.png)



# 3 Basic dark-matter concepts

<!-- comment -->

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
eyJoaXN0b3J5IjpbNjEzNDQwMTg4LDYyNTQ5NzQwNywtNzUyMj
cwNjk1XX0=
-->