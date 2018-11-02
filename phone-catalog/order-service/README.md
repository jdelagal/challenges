# Order Service

Service's responsibility is to create orders.

Given service communicates with the `catalog-service` to check if pre-ordered phones exist in a database.

## Endpoints
+ `GET /api/orders` - fetch a collection of completed orders
+ `POST /api/orders` - add a new order to a database
  + Request payload example
    ```json
    {
      "customerName":"John",
      "customerSurname":"Doe",
      "email":"john@doe.com",
      "orderDetails":[
        {
          "id":"5b047bc3471afa0001fd6043",
          "amount":3
        },
        {
          "id":"5b047bd0471afa0001fd6044",
          "amount":2
        }
      ]
    }
    ```
## App requirements
+ Spring Boot 2
+ Spring Framework 5
+ JDK 10
+ Maven

