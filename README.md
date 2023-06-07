# SimpleApiTest


**Tech Stack**
* Programming Language: Java 17
* Build Tool : Maven
* Logging: Logback via Slf4j
* Test framework: Junit5
* Lombok
* Awaitility (to handle sync issues)
* Jackson (For marshalling and unmarshalling)
* RestAssured
* OkHttp


**How to run test in local**
* Clone the project
* Run `mvn clean install`
* In addition we can pass two environment variables 
    1. -Daut.env : To read configuration from properties file, defaults to Staging environment
    2. -Dapi.wait.time : Api wait time, defaults to 30 seconds
    
**Api Environment**
* Project [VideoGameDB](https://videogamedb.uk/swagger-ui/index.html) public apis to test
 
