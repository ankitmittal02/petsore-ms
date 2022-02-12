# Getting Started with PetStore Application

<!-- GETTING STARTED -->
## Getting Started

 This project is created to manage pet store. To add/remove pets from petstore. 

### Prerequisites

This is an example of how to list things you need to use the software and how to install them.
* Download Java 11 
* Download and have docker running on local machine
* Download and have postman running 
* Download and have 

### Installation
 Clone the repo
   sh
   git clone https://github.com/ankitmittal02/petsore-ms.git
   
### Project Structure
	Following packages have been created as part of this application:
	1) Config
	2) Controller
	3) Model
	4) Service

### Steps to run docker 
	
	Go to the project directory through command line and run following steps: 
	mvn clean package
	docker run -d --name petstore-ms -p 8080:8080 petstore-ms
	docker images

	docker logs select_image_id -f

### Steps to run Kafka on local machine 

	Start the zookeeper:
	.\bin\windows\zookeeper-server-start.bat C:\kafka_2.13-3.1.0\config\zookeeper.properties
	
	Start the kafka server:
	kafka-server-start.bat C:\kafka_2.13-3.1.0\config\server.properties
	
	Create a kafka topic
	bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 	--	partitions 1 --topic mytopic

## Run the following ends points from postman to verify the end points
	
	GET http://localhost:8080/pets/1
	
	GET http://localhost:8080/pets
	
	POST http://localhost:8080/pets
	
	POST http://localhost:8085/produce/Welcome to Kafka