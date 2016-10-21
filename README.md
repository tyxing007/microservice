# CCRM application

## How to start
At first, install npm package for the project. For that you need have installed node.js and gulp globally.

```
npm install
```

Then run the application:

```
gradlew bootRun
```

## Development mode
For front-end development good practice is using gulp watch: when on change javascript, html or css in webapp directory starts gulp handling task. For that run:

```
gulp watch
```

Or you can each time start handling by yourself, using default gulp command:

```
gulp
```

In this way, all changes in [webapp directory](/src/main/webapp) will be synchronized with [static directory](/src/main/resources/static/) of Spring Boot. And run application in debug mode:

```
gradlew bootRun
```
###Deployment
To deploy application ensure that mysql is run and accessible
on localhost:3306 and schema dengo_erp is present.
On start application will create necessary tables if they are absence.
Execute:
``
 ./deploy-app.sh stop; ./deploy-app.sh start
``



####Static code analyze on [SonarQube](http://www.sonarqube.org/)
Require at least an accesible SonarQube server instance locally
```
gradle sonarqube \
 -Dsonar.jdbc.url=jdbc:postgresql://localhost:5432/sonar \
 -Dsonar.verbose=true
```


## Technologies
Server-side:
- Java 8
- Spring Boot 1.3.5
- MySQL (main DB)
- Gradle build tool

Client-side:
- Angular JS
- Angular-Material (UI components)
- Npm JS
- Gulp JS task runner
- SCSS preprocessor


