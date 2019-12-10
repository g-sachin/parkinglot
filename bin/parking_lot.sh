#!/bin/sh
arg1=$1
##directory where jar file is located    
dir=target
##jar file name
jar_name=parkinglot-0.0.1-SNAPSHOT.jar

mvn clean install 

java -jar $dir/$jar_name $arg1
