# PestoProject

# Running project using docker
This is sample spring boot project
1. Clone this project
2. If Docker is not present download from: https://www.docker.com/products/docker-desktop/
3. Run microservices through docker-compose.yml file use the command:
   `docker-compose up`
4. Wait until the project is up and running 
5. Import the above collection into your postman and use

   1. Create User API to create user.
   2. Before any API call you will have to create jwt token using authenticate api and use it into header of every API.
   set postman host Url to Host Url: `http://127.0.0.1:8080`

6. Open these Urls into your browser
Zipkin Url: `http://localhost:9411/zipkin/`
Eureka Url: `http://localhost:8761/`


# Opening Project into your intellij

First download java 17: https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html
Use this java in run configuration to run the project.

 If you want to directly work with project open each folder into intellij Idea
1. Open Intellij Ide
2. First import microservice ServiceReg, Run it
3. Import next microservice Gateway, Run it
4. Import next microservice AuthenticationService, Run it
5. Import next microservice ProductManagementService, Run it
6. Import next Microservice OrderManagementService, Run it


# Requirements covered
1. Microservice architecture: AuthenticationService, ProductManagementService, OrderManagementService. Other microservices: ServiceReg -"Service Registry", Gateway-"Gateway to route to all other microservices from single exposed Url"
2. Can create User with role `ADMIN` | `USER`. Crud APIs for Product, and Orders
2. Gateway authenticates each request Bearer token through AuthenticationService using http rest based inter process communication.
3. Postman GetOrderDetails API, http rest based inter process communication between OrderManagementService and ProductManagementService.
   `https://www.postman.com/payload-saganist-15979257/workspace/shriram-public-workspace/collection/15114630-82391ca9-bd8b-4449-9b54-324c3fc662ca?action=share&creator=15114630&active-environment=15114630-47734e7e-8720-40e3-9611-7b5d0a444ae1`
4. Only ADMIN user can add products and modify order status. -- role based access
5. Normal USER can order and view all its orders, and cancel them.
6. Optimistic Locking mechanism to prevent concurrent update of resources and maintain the itegrity... We can find their Unit test-cases in ProductManagementService, and OrderManagementService
7. Distributed log tracing using zipkin, and overall microservices instances status using Eureka cloud
   Zipkin Url: `http://localhost:9411/zipkin/`
   Eureka Url: `http://localhost:8761/`
8. Used clean code, emus, exceptions gracefully.
9. Added api rate-limiting using Resilience4j
10. Docker based MySql server, other Spring Boot microservices.
11. We can also add new instances of a service everyting is load balaced and gracefully handeled.
   

Resources Referred:
Optimistic locking
https://medium.com/@soyjuanmalopez/conquering-concurrency-in-spring-boot-strategies-and-solutions-152f41dd9005