FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/WebScrapper-0.0.1-SNAPSHOT.jar /app/webscraper.jar
ENTRYPOINT ["java","-jar","/app/webscraper.jar"]
EXPOSE 8080