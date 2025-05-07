FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/springboot-oauth2-client2-0.0.1-SNAPSHOT.jar springboot-oauth2-client2.jar
COPY target/springboot-oauth2-client2-0.0.1-SNAPSHOT.jar springboot-oauth2-client2-0.0.1-SNAPSHOT.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "springboot-oauth2-client2-0.0.1-SNAPSHOT.jar"]