#!/bin/bash

# Build script for Apache PDF project
# This script compiles all Java source files with required dependencies

# Create output directory if it doesn't exist
mkdir -p out

# Compile Java files with all required libraries (PDFBox 3.0.6 + Jackson)
javac -cp "library/pdfbox-app-3.0.6.jar:library/jackson-core-2.15.2.jar:library/jackson-databind-2.15.2.jar:library/jackson-annotations-2.15.2.jar" \
      -d out \
      src/src/*.java

# Check if compilation was successful
if [ $? -eq 0 ]; then
    echo "Build successful! Compiled classes are in the 'out' directory."
else
    echo "Build failed! Check the error messages above."
    exit 1
fi
