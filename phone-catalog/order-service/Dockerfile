FROM openjdk:10-jdk

ADD ./target/order-service-*.jar app.jar

ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb-order-service:27018/orders","-Dphone-catalog.service.url=http://catalog-service:8087","-jar","/app.jar"]