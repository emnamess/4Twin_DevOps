FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/*.jar student-management-0.0.1.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
