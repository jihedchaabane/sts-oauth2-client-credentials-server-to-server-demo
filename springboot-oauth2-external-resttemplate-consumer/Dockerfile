FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/springboot-oauth2-external-resttemplate-consumer-0.0.1-SNAPSHOT.jar springboot-oauth2-external-resttemplate-consumer.jar
COPY target/springboot-oauth2-external-resttemplate-consumer-0.0.1-SNAPSHOT.jar springboot-oauth2-external-resttemplate-consumer-0.0.1-SNAPSHOT.jar
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "springboot-oauth2-external-resttemplate-consumer-0.0.1-SNAPSHOT.jar"]