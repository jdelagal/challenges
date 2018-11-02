# Catalog Service

Service's responsibility is to store and fetch phones data.

## Endpoints
+ `GET /api/phones` - fetch a collection of available phones
+ `GET /api/phones?idSeq={idSeq}` - fetch a collection with given ids
+ `POST /api/phones` - add a new phones to a database
  + Request payload example
    ```json
    {
      "name":"Smartphone 1",
      "description":"Phone description",
      "imageReference":"http://image-store.com/phone-image.png",
      "price":154.3
    }
    ```

## App requirements
+ Spring Boot 2
+ Spring Framework 5
+ JDK 10
+ Maven

