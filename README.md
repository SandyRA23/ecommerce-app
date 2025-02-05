Multi-Warehouse E-Commerce Application

Overview 

The Multi-Warehouse E-Commerce Application is a backend system designed to manage inventory, orders, and warehouse operations for an e-commerce platform with multiple warehouses. This application provides a robust and scalable solution for handling complex inventory management, order fulfillment, and warehouse operations.

The application is built using Java and Spring Boot, following a Domain-Driven Design (DDD) approach. It is designed to be modular, extensible, and easy to maintain.

Features
1. Warehouse Management
   - Create, update, and manage multiple warehouses.
   - Track warehouse capacity and current inventory levels. 
   - Manage warehouse operational status (e.g., ACTIVE, INACTIVE, CLOSED).

2. Inventory Management
   - Add, update, and remove items from warehouse inventory. 
   - Track product quantities across multiple warehouses. 
   - Validate inventory capacity before adding new items.

3. Order Management
   - Create and manage customer orders. 
   - Assign orders to the nearest or most suitable warehouse. 
   - Track order status (e.g., PENDING, SHIPPED, DELIVERED).

4. Event-Driven Architecture
   - Use Kafka for event-driven communication between services. 
   - Publish and consume events such as order creation, inventory updates, and warehouse status changes.

5. Exception Handling
   - Global exception handling for consistent error responses. 
   - Custom exceptions for domain-specific errors (e.g., insufficient inventory, warehouse capacity exceeded).

6. Modular Design, separated into multiple modules:
   - Warehouse Service: Manages warehouse operations and inventory. 
   - Order Service: Handles order creation and fulfillment. 
   - Inventory Service: Tracks inventory across warehouses. 
   - Each module is independently deployable and scalable.

Technologies Used
- Java 17: Primary programming language. 
- Spring Boot: Framework for building the application. 
- Spring Data JPA: For database interactions. 
- Kafka: For event-driven communication. 
- PostgreSQL: Relational database for storing warehouse, inventory, and order data. 
- Lombok: For reducing boilerplate code. 
- Maven: For dependency management and build automation. 
- Docker: For containerization and deployment.
- Kubernetes: Container orchestration for scaling and managing microservices. 
- Google Cloud Platform (GCP)

Getting Started

Prerequisites
- Java 17 or higher 
- Maven 3.6 or higher 
- Docker (optional, for running Kafka and PostgreSQL in containers)
- Kafka (optional, for event-driven communication)

Installation
- Clone the Repository:

Build the Application:

 - mvn clean install

Run the Application:

- mvn spring-boot:run


Run Kafka and PostgreSQL using Docker:

- docker-compose up -d


Configuration

Update the application.yml file to configure database connections, Kafka topics, and other settings.

Author : Sandy Rachman Adrian
