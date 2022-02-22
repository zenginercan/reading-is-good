Reading Is Good Application Guide
----------------------------------------------

Reading Is Good is an application written for ReadingIsGood online book retail firm.
Basically users can make general operations about customer, books and orders.

Tech Stack
------------
Java 11  
Maven 3.8.1  
Spring Boot 2.5.0  
MongoDB 5.0  
Swagger  


Running
------------
Be sure that Docker is installed on your computer and you've copied docker-compose.yml to your computer.

Then you can find the Docker image at: https://hub.docker.com/repository/docker/developercan/reading-is-good

From command line interface, you can get the image with this command:

docker push developercan/reading-is-good:latest

In the folder that docker-compose.yml takes place, run the following command in terminal. (Terminal application Mac and 
Linux, Command.exe in Windows)

docker-compose up

When the Mongo DB and API containers are created you're ready to send requests. Follow up the logs.

All requests will be exposed at http://localhost:8080

All details about endpoints can be found at http://localhost:8080/swagger-ui.html

Anyway, let's have a quick look for some services.

http://localhost:8080/customer/create

You can create a new customer with this endpoint.

The request must be look like this:

```{  
    "firstName":"Ada",  
    "lastName":"Lovelace",  
    "email":"d@d.com",  
    "address":"New York",  
    "phoneNumber":5353333344,  
    "password":"xyz12"  
}
```

The email and phoneNumber values have to be not used before another customer.

http://localhost:8080/book/create

You can create a new book with this endpoint with POST method.

The request must be look like this:

```{
    "name": "Yaban",
    "author": "Yakup Kadri Karaosmanoğlu",
    "isbn": "978-090-87-65",
    "price": 40,
    "status": 1,
    "stockCount": 100
}
```

To try out other endpoints you often need to choose a customer or book. Don't worry;
we have endpoints to get all customers or to get all books.

http://localhost:8080/customer/

If you send request this endpoint with GET method, you can list all customers created so far.

Also we have similar endpoint for books: http://localhost:8080/book/  
    Of course you must send request with GET method.



http://localhost:8080/order/create

You can create a new order with this endpoint with POST method.

The request must be look like this:

```{
    "name": "İlk Sipariş",
    "itemList": [{
        "bookId": "61114ae86e531232fb8ee5ce",
        "orderCount": 2
    }],
    "owner": {
        "id": "61114ef18dc74618a7f27b5f",
        "firstName": "Ada",
        "lastName": "Lovelace",
        "birthDate": "2021-08-09T15:51:13.577+00:00",
        "email": "d@d.com",
        "address": "New York",
        "phoneNumber": 5353333344,
        "password": "xyz12",
        "status": 1,
        "creationDate": "2021-08-09T15:51:13.578+00:00"
    }
}
```

http://localhost:8080/statistics/monthly?phoneNumber=5353333344

You can get statistics for the customer has given phone number
at phoneNumber query parameter.

Response should be like this:

```{
    "status": "OK",
    "message": "Getting monthly statistics for customer is successful",
    "responseBody": {
        "AUGUST": {
            "totalOrderCount": 1,
            "totalBookCount": 5,
            "totalPurchasedAmount": 200
        }
    }
}
```



All endpoints and requests examples can be found in ReadingIsGood.postman_collection file.
