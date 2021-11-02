FROM adoptopenjdk/openjdk11:alpine
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]