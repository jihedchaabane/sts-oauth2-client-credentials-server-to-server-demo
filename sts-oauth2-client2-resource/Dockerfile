FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/sts-oauth2-client2-resource-0.0.1-SNAPSHOT.jar sts-oauth2-client2-resource.jar
COPY target/sts-oauth2-client2-resource-0.0.1-SNAPSHOT.jar sts-oauth2-client2-resource-0.0.1-SNAPSHOT.jar
EXPOSE 7771
ENTRYPOINT ["java", "-jar", "sts-oauth2-client2-resource-0.0.1-SNAPSHOT.jar"]