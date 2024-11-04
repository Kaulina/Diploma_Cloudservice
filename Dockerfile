FROM maven:3.9.6-openjdk-23 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package -Dmaven.test.skip=true


FROM openjdk:23-slim
WORKDIR /app
COPY target/Diploma_Cloudservice-0.0.1-SNAPSHOT.jar /target/ROOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/target/ROOT.jar"]