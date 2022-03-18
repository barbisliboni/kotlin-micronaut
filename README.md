# kotlin-micronaut

## How does it work?
**store-service** will be responsible for all sale information, and when it generates a sale, the data will be stored at Kafka database. 

**vehicle-service** will have a **PostgreSQL** database, and will be responsable for all vehicle information.

**report-service** will consume **Kafka** data and store it at MongoDB. It will be responsible to store all the transaction between the other two microservices. 

the **circuit break** will communicate with **Redis**, which will be a fall back method. Also, when vehicle-service won't be consistent, the request will be stored at him. 

everything will be encapsulated in a container, what means that the client won't access directly the microservices, but the **API Gateway** microservice. This **API Gateway** will access **Consul**, which will forward the requests to the microservices ecosystem.

## Methods
**GET /vehicle-service/vehicles/{id}**

**POST /vehicle-service/vehicles**
```
{
    "brand": "Fiat",
    "model": "Toro",
    "licensePlate": "ABCC54635"
}
```
**POST /sales**
```
{
    "client": "Bárbara",
    "vehicle": "1",
    "price": "25000",
    "installments": 10
}
```
**GET /report-service/sales**
### Arguments

**id:** the vehicle id 

### Examples

http://localhost:9090/vehicle-service/vehicles/1

http://localhost:9090/vehicle-service/vehicles

http://localhost:9090/report-service/sales


### Response Body

201 - Created




## How to use it?

First, run ```docker network create micronaut-net``` to create a Docker network, and right after, run  the **docker-compose.yml** file to run all the containers:
```docker-compose up -d```. Don't forget to run these: <br>

```docker run -d --name zookeeper-server --network micronaut-net -e ALLOW_ANONYMOUS_LOGIN=yes bitnami/zookeeper:latest```<br>
```docker run -d --name kafka-server --network micronaut-net -e ALLOW_PLAINTEXT_LISTENER=yes -e KAFKA_CFG_ZOOKEEPER_CONNECT=zookeeper-server:2181 bitnami/kafka:latest```

After, go to the project, open all four microservices and open the Gradle right side menu, **Execute Gradle Task** and run ```gradle clean build -x test```. It will build the project as a .jar file. 

Then, you will open your terminal and go to the folder and file where *x* microservice is (one by one), starting with **api-gateway**.

Generating **api-gateway** image:<br>
```docker build -t api-gateway:v1 .``` 

Uploading **api-gateway** container:<br>
```docker run -p 9090:9090 --name api-gateway --network micronaut-net api-gateway:v1```

Generating **vehicle-service** image:<br>
```docker build -t vehicle-service:v1 .```

Uploading **vehicle-service** container:<br>
```docker run -P --network micronaut-net vehicle-service:v1```

Generating **store-service** image:<br>
```docker build -t store-service:v1 .```

Uploading **store-service** container:<br>
```docker run -P --network micronaut-net store-service:v1```

Generating **report-service** image:<br>
```docker build -t report-service:v1 .```

Uploading **report-service** container:<br>
```docker run -P --network micronaut-net report-service:v1```

After all this, you will be able to see all microservices on **Consul** **http://localhost:8500/**. With **Consul**, when the microservices are up, they will be registered at *Consul*, specifying its address, therefore, when a microservice wants to communicate with other, it just gets the address on **Consul**.

To check the data on **MongoDB**, run this commands:<br>
```docker run -it --rm --network micronaut-net mongo mongo --host ms-mongo -u root -p e296cd9f --authenticationDatabase```<br>
```admin sales```<br>
```db.sale.find({})```
