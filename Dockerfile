FROM openjdk:21-oracle

ARG JAR_FILE=target/os-file-manager-1.0.0.jar

ADD ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]