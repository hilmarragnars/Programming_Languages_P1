#!/bin/bash
javac *.java
java Compiler < "$@" | java Interpreter

