# RestAssured Test Suite for Sogeti Automation Task API Tetst by Jon Paulo Ojon
## Overview
This test suite includes automated API tests for http://api.zippopotam.us/de/bw/stuttgart and Data Driven Test for http://api.zippopotam.us/{country}/{postal-code}

## Author
Jon Paulo Ojon

## Prerequisites
* Java: (v22 recommended), find installer on https://www.oracle.com/java/technologies/downloads/ site
* Maven: (optional)
* IDE: Optional but recommended (e.g., IntelliJ IDEA, Eclipse).

## Installation
1. Clone the repository and go to project directory
- git clone https://github.com/jp-ojon/sogeti-ui-test-auto-jon.git
- change directory to root folder apitestautojon

## Test Cases
- Test Case 1: Write API Test for http://api.zippopotam.us/de/bw/stuttgart
- Test Case 2: Write API Data Driven Test for http://api.zippopotam.us/{country}/{postal-code}

## Project Structure
- src/test/java: Contains the test code.
- src/test/resources: Contains test resources (e.g., configuration files).
- pom.xml: Maven build configuration file.
- testng.xml: Configuration of tests

## Running Tests
Use the following commands in any terminal or cmd line to run tests
1. mvnw.cmd test    : run all tests for Windows, no Maven installation needed
2. ./mvnw test      : run all tests for Unix/Linux/Mac, no Maven installation needed
3. mvn test         : run all tests for any OS, Maven installation required

## Configuration
**To be added**

## Links to Documentation
- RestAssured: https://rest-assured.io/
- TestNG: https://testng.org/
- Maven: https://maven.apache.org/guides/index.html
