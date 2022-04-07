# Expense Reimbursement System

## Description

The Expense Reimbursement System (ERS) will manage the process of reimbursing employees for expenses incurred while on company time. All employees in the company can login and submit requests for reimbursement and view their past tickets and pending requests. Finance managers can log in and view all reimbursement requests and past history for all employees in the company. Finance managers are authorized to approve and deny requests for expense reimbursement.

## Links

👉 Frontend repo: https://github.com/tientle84/project1-frontend.git

👉 Frontend demo: [https://reimbang.herokuapp.com/](https://reimbang.herokuapp.com/)

## Technologies Used

### Database
* [Postgres 14.2](https://www.postgresql.org/) - PostgreSQL is a powerful, open source object-relational database system.
* [Google Cloud Platform SQL](https://cloud.google.com/sql) - Fully managed relational database service for MySQL, PostgreSQL, and SQL Server.

### Backend
* [Java SE 8](https://www.oracle.com/java/technologies/javase/javase8u211-later-archive-downloads.html) - The JDK is a development environment for building applications using the Java programming language.
* [Javalin 4.3.0](https://javalin.io/) - A simple web framework for Java and Kotlin.
* [JUnit 5](https://junit.org/junit5/) - The 5th major version of the programmer-friendly testing framework for Java and the JVM.
* [Mockito 4.3.1](https://site.mockito.org/) - Tasty mocking framework for unit tests in Java.
* [JDBC 4.3](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) - Java-based data access technology used for Java database connectivity
* [JWT 0.11.2](https://jwt.io/) - JSON Web Token (JWT) is a compact URL-safe means of representing claims to be transferred between two parties
* [Google Cloud Storage](https://cloud.google.com/storage) - Object storage for companies of all sizes. Store any amount of data. Retrieve it as often as you’d like.
* [SLF4J 2.0.0](https://www.slf4j.org/) - The Simple Logging Facade for Java.

### Frontend
* [Angular 13](https://angular.io/) - The modern web developer's platform
* [Angular Material 13](https://material.angular.io/) - Material Design components for Angular

### CI/CD
* [Jenkins](https://www.jenkins.io/) - Jenkins provides hundreds of plugins to support building, deploying and automating any project.
* [SonarQube](https://sonarcloud.io/) - SonarQube is an open-source platform developed by SonarSource for continuous inspection of code quality and security
* [Docker](https://www.docker.com/) - Docker is an open platform for developing, shipping, and running applications.
* [Google Cloud Compute Engine](https://cloud.google.com/compute) - Secure and customizable compute service that lets you create and run virtual machines on Google’s infrastructure.

## Features

### Employee User
* Register
* Login
* Submit reimbursement
* Edit pending reimbursement
* Delete pending reimbursement

### Manager User
* Register
* Login
* Review all the reimbursements
* Filter reimbursements by status (pending, approved, denied)
* Authorize pending reimbursements (approve or deny)

### Todos:
* Create user profile
* Create notification function (when reimbursements get approve or deny)

## Getting Started

### Postgres environment variables setup

![postgres-env-setup](/imgs/postgres_env_setup.png)
   
### Backend
```
$ git clone https://github.com/tientle84/project1.git
$ cd project1
$ gradle build
$ cd build/libs
$ java -jar project1-1.0-SNAPSHOT.jar
```

### Frontend
```
$ git clone https://github.com/tientle84/project1-frontend.git
$ cd project1-frontend
$ npm install
$ ng serve
```

## Usage

### Authentication

![register](/imgs/register.png)

![login](/imgs/login.png)

### Employee user

![employee_page](/imgs/employee_page.png)

![employee_new_request](/imgs/employee_new_request.png)

### Manager user

![manager_page](/imgs/manager_page.png)

## License

This project is licensed under the [MIT](https://choosealicense.com/licenses/mit/) license.
