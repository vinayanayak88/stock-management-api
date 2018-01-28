FROM openjdk:8
ADD target/stock-management-api.jar stock-management-api.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "stock-management-api.jar"]