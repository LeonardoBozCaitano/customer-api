<h3 align="center"> 
	Bank Customer API
</h3>

<p align="center">
  <a href="#woman_technologist-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#mag_right-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#information_source-how-to-use-it">How to use it</a>&nbsp;&nbsp;&nbsp;
</p>

## :woman_technologist: Project

REST API to manage customer information and wallets movimentation.

## :mag_right: Technologies

- Java 17
- Gradle
- Spring Boot Starter Web
- Lombok
- Swagger
- Docker and Docker-compose
- JUnit 5
- Mockito

## :information_source: How to use it

### Clone the project to your machine
```bash
# using this command
$ git clone https://github.com/mvolinger/mobility-api.git
```

### Running it with a Docker-compose
```bash
# Run docker compose-up to set up the environment
$ docker-compose up
# Build the container image
$ docker build -t bank/customer-api .
# Run
$ docker run -dp 8080:8080 bank/customer-api
```

### To get all requests you can
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/8824516-bc89913b-b958-467e-af73-2cd15b6d72c4?action=collection%2Ffork&collection-url=entityId%3D8824516-bc89913b-b958-467e-af73-2cd15b6d72c4%26entityType%3Dcollection%26workspaceId%3D1e44a8e7-20a7-439f-9475-1a08fb462b0f#?env%5BLOCAL%5D=W3sia2V5IjoiRU5EUE9JTlQiLCJ2YWx1ZSI6ImxvY2FsaG9zdDo4MDgwIiwiZW5hYmxlZCI6dHJ1ZX0seyJrZXkiOiJDVVNUT01FUl9JRCIsInZhbHVlIjoiIiwiZW5hYmxlZCI6dHJ1ZX1d)

---
Made by Leonardo Boz Caitano :wave: [Get in touch!](https://github.com/LeonardoBozCaitano/)