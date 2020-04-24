##RestAPI Testing
- This Project to Automate the RestAPI's and Verifies the response of the API.
- The framework is built using RestAssured and TestNG.

## Stack
* Language: Java
* BuildTool : Gradle
* TestFramework: TestNG

* Packages Included
    - RestAssured
    - TestNG
    - gson
    - aeonbits.owner


### Run
```$xslt
./gradlew test
```
###Properties
- Environment to test is configure from SystemProperty($env).
- Endpoints are configured in $env.properties.
