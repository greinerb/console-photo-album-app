# Console Application for displaying info related to photo albums


This application at startup loads photo albums in memory and dislays id and title information based on photo albums you choose to view.


# Tech
#### Spring Boot
  - Spring Boot makes it easy to create stand-alone, production grade Spring based applications that you can "just run".
#### Java
  - Java version 1.8
#### Sonar-JaCoCo
  -   It is an open-source toolkit for measuring and reporting Java code coverage and offers instructions, line and branch coverage.
  -   SonarQube JaCoCo plugin — one of the defaults for coverage analyses within the code quality management platform SonarQube.
  -   This plugin allows you to see real-time what is happening with the project but results can easily be integrated/forwarded to a SonarQube instance and see historical reporting.
#### Maven
  - Maven needs to be downloaded and installed on your workstation.  You will need this work outside of the IDE
  - Maven [quickstart](http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)

#### Commands to build/test/package/run the project
- ```mvn clean compile```
This will build the project from source
- ```mvn clean compile spring-boot:run```
This will build and run the project from source
- ```mvn clean jacoco:prepare-agent test jacoco:report```
This will run build and run the test cases against the project and then run jacoco and generate static code coverage.
The report for this will be located in the following relative location of the project.
./target/site/jacoco/index.html
- ```mvn clean compile test package```
This will build and package up the code as what is called an uber-JAR (also known as a fat JAR or JAR with dependencies that contains not only the compiled code, but embeds all of its dependencies as well. This means that the JAR functions as an "all-in-one" distribution of the software, without needing any other Java code)
In order to run this, you would navigate to this location and execute this command
-- ```./target/```
--```java -jar console-app-1.0.0-SNAPSHOT.jar```



### Installation

This requires [Maven](http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html) to run.  It also requires a console to be readily available and has to be ran outside of the IDE as the ```System.console()``` is not available within IDE's like eclipse.  So you can either run it as a spring boot application via maven from the command line or you can run it as a java process from the command line.  Both commands are detailed above.


### Todos

 - Write MORE Tests

License
----

MIT

