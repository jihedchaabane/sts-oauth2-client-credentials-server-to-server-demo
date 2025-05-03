FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/sts-spring-boot-resource-server-0.0.1-SNAPSHOT.jar sts-spring-boot-resource-server.jar
COPY target/sts-spring-boot-resource-server-0.0.1-SNAPSHOT.jar sts-spring-boot-resource-server-0.0.1-SNAPSHOT.jar
EXPOSE 7773
ENTRYPOINT ["java", "-jar", "sts-spring-boot-resource-server-0.0.1-SNAPSHOT.jar"]