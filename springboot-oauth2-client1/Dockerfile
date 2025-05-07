FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/springboot-oauth2-client1-0.0.1-SNAPSHOT.jar springboot-oauth2-client1.jar
COPY target/springboot-oauth2-client1-0.0.1-SNAPSHOT.jar springboot-oauth2-client1-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "springboot-oauth2-client1-0.0.1-SNAPSHOT.jar"]