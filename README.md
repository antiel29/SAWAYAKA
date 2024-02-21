<h3 align="center">
    <img src="https://raw.githubusercontent.com/antiel29/SAWAYAKA/main/Frontend/SAWAYAKA/src/assets/images/cat_zakuro.png" width="100" alt="Logo"/><br/>
    Sawayaka
</h3>

<p>Sawayaka is a REST API forum inspired on <a href="https://store.steampowered.com/app/658620/Wonderful_Everyday_Down_the_RabbitHole/" target="_blank">Subarashiki hibi<a/>. This api allow users to register ,create threads, and engage in discussions through comments. </p>

<h3 align="center">Video demostration</h3>

<div align="center">
  <a href="https://www.youtube.com/watch?v=ComMy8z6juI"><img src="https://img.youtube.com/vi/ComMy8z6juI/0.jpg" alt="Youtube image"></a>
</div>

## Table of Contents

- [Technologies](#technologies)
- [Features](#features)
- [Requirements](#requirements)
- [Configuration](#configuration)

## Technologies

- **Backend:** Spring Boot (JAVA 21).
- **Frontend:** React.
- **Security:** Spring boot Security.
- **Authentication:** JWT.
- **Database:** MySQL.
- **ORM:** Hibernate.
- **Queries:** HQL.

## Features

- **Swagger Documentation** with anotations to test all the endpoints.
- **Seed** with the information i gathered of the game forum.
- **Token Blacklist** that allows users to logout and invalidate the token.
- **Role-Based Authorization** admin/user roles.

## Requirements

- [Java 21](https://www.oracle.com/ar/java/technologies/downloads/)
- [Maven](https://maven.apache.org/download.cgi) (for building and managing Java dependencies)
- [Node.js](https://nodejs.org/) and [npm](https://www.npmjs.com/) (for React frontend)
- [MySQL](https://www.mysql.com/downloads/)

## Configuration

**Step 1:Clone the repository**

Clone the repository to your local machine.

```bash
git clone https://github.com/antiel29/SHIBANK.git
```

**Step 2:Set Up MySQL**

Create a database and update the connection properties in Backend/SAWAYAKA/src/main/resources/application.properties.

**Step 3:Build and Run the Backend**

Execute the following commands in the Backend/SAWAYAKA directory to build the Spring Boot backend:

```bash
mvn clean install
```

To run it

```bash
java -jar target/SAWAYAKA-1.0-SNAPSHOT.jar
```

**Step 4:Build and Run the Frontend**

Execute the following commands in the Frontend/SAWAYAKA directory and install dependencies (only needed the first time):

```bash
npm install
```

To run it

```bash
npm run dev
```
