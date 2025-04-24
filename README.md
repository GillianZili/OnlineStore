## Overview

This is the backend server for the online grocery store. It provides various RESTful APIs for managing items, users, carts, orders, and checkout operations. The backend is built using **Spring Boot** and **MySQL**.

---

## Prerequisites

Before launching the backend server, make sure you have the following installed:

1. **Java** (version 11 or higher)  

2. **Maven**

3. **MySQL Database**

---

## Launch the Backend Server

### Step 1: Clone the Repository

Clone the backend project to your local machine:

```
git clone <your-repository-url>
cd <your-repository-directory>

## The command line of launching MySQL:
& "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysql" -u root -p   
```

### Step 2: Configure the Application
Make sure to configure your application.properties or application.yml file with your database credentials.
```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
### Step 3: Get your ebay API token
Since the data of items is fetched from eBay, you have to get your ebay API for further work.\
Please follow the official instruction: https://developer.ebay.com/develop/get-started

### Step 4: launch the server
To start the backend server, run the following command in your terminal:
```
./mvnw spring-boot:run
```
---

## Testing the APIs with Postman
All API endpoints can be found in the OnlineStore\src\main\java\OnlineStore\controller folder and can be tested via Postman directly. \
Please note that the backend, configured with Spring Boot, runs on port 8080 by default.
### json requests for 2 APIs
### @PostMapping("/item/add")
```
{
    "id": "xxxx",
    "name": "xxxx",
    "price": xx,
    "storage": xx
}
```
### @PostMapping("/cart/update")
```
[
  {
    "user_id": xx,
    "item_id": "xxxxx",
    "amount": xx
  },
  {
    "user_id": xx,
    "item_id": "xxxxx",
    "amount": xx
  }
]
```