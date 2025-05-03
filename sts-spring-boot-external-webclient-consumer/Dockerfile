FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/sts-spring-boot-external-webclient-consumer-0.0.1-SNAPSHOT.jar sts-spring-boot-external-webclient-consumer.jar
COPY target/sts-spring-boot-external-webclient-consumer-0.0.1-SNAPSHOT.jar sts-spring-boot-external-webclient-consumer-0.0.1-SNAPSHOT.jar
EXPOSE 7774
ENTRYPOINT ["java", "-jar", "sts-spring-boot-external-webclient-consumer-0.0.1-SNAPSHOT.jar"]