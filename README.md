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

As a result of this, you will see that various artifacts are created in your local Maven repository.
In the case of this example you'll see the following artifacts in `~/.m2/repository/com/example/xdsl/xdsl/0.0.1-SNAPSHOT`:
* `xdsl-0.0.1-SNAPSHOT.jar`
* `xdsl-0.0.1-SNAPSHOT.pom`
* `xdsl-0.0.1-SNAPSHOT-shaded.jar`

Of these, the most important is the `shaded` JAR - this is an executable JAR that can be provided to users of your DSL.

If you create an alias like: <br>
`alias xdsl='~/.m2/repository/com/example/xdsl/xdsl/0.0.1-SNAPSHOT/xdsl-0.0.1-SNAPSHOT-shaded.jar'`

And then run `xdsl` you'll see the help associated with the 

## 2.3 Generated code structure

![run bootstrap](images/generated-structure.png)


# 3 Basic dark-matter concepts

<!-- comment -->

# Appendix: Other Notes

## Maven Warnings

Within Eclipse, you may see a warning like:

![run bootstrap](images/maven-warnings.png)

You may safely ignore these warnings - but if you want further information on them, see the following Stack Overflow question: [Eclipse Maven: SLF4J: Class path contains multiple SLF4J bindings](https://stackoverflow.com/questions/63518376/eclipse-maven-slf4j-class-path-contains-multiple-slf4j-bindings)

<!--stackedit_data:
eyJoaXN0b3J5IjpbNjEzNDQwMTg4LDYyNTQ5NzQwNywtNzUyMj
cwNjk1XX0=
-->