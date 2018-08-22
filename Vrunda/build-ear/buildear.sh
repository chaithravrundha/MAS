#!/bin/sh

cp target/Vrunda.war .
jar -cvf Vrunda.ear META-INF/* *.war
