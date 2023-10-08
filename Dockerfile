FROM openjdk:21-oracle

ARG JAR_FILE=target/seniority-meter-1.0.0.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]