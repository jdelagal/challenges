FROM openjdk:10-jdk
ADD ./target/catalog-service-*.jar app.jar
ENTRYPOINT ["java","-Dspring.data.mongodb.uri=mongodb://mongodb-catalog-service:27019/phones","-jar","/app.jar"]