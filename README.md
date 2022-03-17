<h3 align="center"> 
	Bank Customer API
</h3>

<p align="center">
  <a href="#woman_technologist-project">Project</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#mag_right-technologies">Technologies</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#information_source-how-to-use-it">How to use it</a>&nbsp;&nbsp;&nbsp;
</p>

## :woman_technologist: Project

This project is a Java spring microservice that manages customer information and customer wallet movements. This is the first project of a simple bank application.

The other projects will be:
- transference: This project will asynchronously process transference requests using AWS SQS queues. It will validate the transference and communicate with the customer microservice to create customer wallet movements.
- bank-infrastructure: This project will create all AWS infrastructure using Terraform.

## :mag_right: Technologies

- Java 17
- Gradle
- Spring Boot Starter Web
- Lombok
- Swagger
- Docker and Docker-compose
- JUnit
- Mockito
- WireMock

## :information_source: How to use it

### Clone the project to your machine
```bash
# using this command
$ git clone https://github.com/mvolinger/mobility-api.git
```

### Running it
```bash
# Run docker compose-up to set up the environment
$ docker-compose up

# Build the container image
$ docker build -t bank/customer-api .

# Run
$ docker run -dp 8080:8080 bank/customer-api
```

### To get all requests you can
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/8824516-c08886ab-1caf-43a0-b8b6-428ae4097050?action=collection%2Ffork&collection-url=entityId%3D8824516-c08886ab-1caf-43a0-b8b6-428ae4097050%26entityType%3Dcollection%26workspaceId%3D1e44a8e7-20a7-439f-9475-1a08fb462b0f)
---

Made by Leonardo Boz Caitano :wave: [Get in touch!](https://github.com/LeonardoBozCaitano/)