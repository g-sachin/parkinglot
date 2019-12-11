#!/bin/sh
arg1=$1
##directory where jar file is located    
dir=target
##jar file name
jar_name=parkinglot-0.0.1-SNAPSHOT.jar

#go back to directory where pom.xml is present to compile project
cd ..


# maven exec plugin compile and run java main application
mvn exec:java
