#!/bin/bash
export CLASSPATH=target/sns_aws-1.0-SNAPSHOT.jar
export className=App
echo "## Running $className..."
shift
mvn exec:java -Dexec.mainClass="br.com.sns_aws.$className"