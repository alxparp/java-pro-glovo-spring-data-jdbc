# Glovo Spring

This project is a continuation of the "Java Pro Glovo" project and also it is a educational project.

The purpose of this project is the practice of the technologies which are described below, as well as user registration, sending a token to the mail to confirm the email, display all users after sign in.

Mainly for manipulating data from database is used the "Jpa Repository", extending which, were created specific interfaces. Some methods use HQL-queries.

The services provide opportunities for sending messages to users, to register and search the users, to confirm the tokens (at the same time, it's worth noting, if user didn't pass the confirmation in within a certain number of minutes - the token becomes invalid). In order to debug the code was added the logging and all log's information will be saved in a specific file. The services are working with entities, the controllers - with models, that's why before sending information to the controllers, entites are converted to DTO.

Web-controllers navigate between pages, perform checks, and pass information to the services for processing. The access to these controllers are passing via url using REST-style, it simplifies readability. 

REST-controllers provide app functionality to other developers.

The HTML-documents are used to demonstrate the information to the users. For making dynamic pages the app uses Thymeleaf.

Thanks to Spring Security and written configuration, every request is filtered and provides each user just those resources on which he has the relevant privileges. Passwords are encrypted with BCrypt encription algorithm.

DDL and DML queries were written with the Liquibase migration system.

The majority appliction components are covered with unit and integration tests.

The profiles are used to separate H2DB which is used in development environment and with integration tests, from PostgreSQL, that is used in production. Application defines how to show information exceptions to the users, depending on what profile was selected.

Project setup (н/р MailDev, Hibernate, DBMS, ...) is carried out in such files like application*.yml, in "resources" directory and depends on selected profile is carried out.


## Workflow

1. Download the project from GitHub
2. Create "glovo" database in PostgreSQL DBMS
3. Add and run the MailDev in Docker ($ docker run -p 1080:1080 -p 1025:1025 maildev/maildev)
4. Open the project in IDE
5. Change username and password in application-prod.yml
6. Run project
7. After the registration go to localhost:1080 and confirm the email


## Technologies

* Java SE 17
* Gradle
* Spring Boot
    - Data JPA | JDBC
    - Web
    - Security
    - Mail
    - Test
    - Profiling
* Hibernate, HQL
* PostgreSQL
* Liquibase
* H2DB
* Lombok
* Log4j
* Jakson
* Guava
* GIT
* HTML
* REST
* Thymeleaf
* JUnit, Mockito
* MailDev
