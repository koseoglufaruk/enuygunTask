# Enuygun Automation Framework

This is a custom test automation framework built specifically for Enuygun's flight booking functionality.
The framework is structured to support automated tests for various scenarios including flight search, filtering, and validation.

## Dependencies
- [Allure report](https://allurereport.org/docs/install/) 
- [Java jdk21](https://www.oracle.com/tr/java/technologies/downloads/) 
- [Maven](https://maven.apache.org/download.cgi) 


## Features
- **Parameterized Testing**: Supports dynamic inputs for routes, dates, and passengers.
- **Flight Filters**: Ability to filter flights by time and airline.
- **Reporting**: Generates automated test reports.

## How to run the tests:
1. Clone the repository.
2. Run tests via the command:
   ```
   mvn clean install -DskipTests
   mvn test
   ```

## Structure:
- **src/main**: Contains test logic and helpers.
- **src/test**: Contains test cases and resources.

Enjoy testing!