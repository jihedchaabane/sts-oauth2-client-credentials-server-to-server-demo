FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/sts-oauth2-client1-resource-0.0.1-SNAPSHOT.jar sts-oauth2-client1-resource.jar
COPY target/sts-oauth2-client1-resource-0.0.1-SNAPSHOT.jar sts-oauth2-client1-resource-0.0.1-SNAPSHOT.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "sts-oauth2-client1-resource-0.0.1-SNAPSHOT.jar"]