#!/bin/sh
arg1=$1
#directory where jar file is located    

#jar file name
jar_name=parkinglot-0.0.1-SNAPSHOT.jar

#go back to directory where pom.xml is present to compile project
cd ..

# compile
mvn clean install

# maven exec plugin to run java main application
mvn exec:java
