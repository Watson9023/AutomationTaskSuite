#!/bin/bash

# Replace paths and options with actual values
classpath="/path/to/your-classpath"
baseUrl="https://ultimateqa.com/"
driverPath="/path/to/chromedriver"
browserName="chrome"

# Running Java application with the provided options
java -cp "$classpath" com.voss.section2.tests.PageTitleAndScreenshotTest -h "$baseUrl" -d "$driverPath" -b "$browserName"
