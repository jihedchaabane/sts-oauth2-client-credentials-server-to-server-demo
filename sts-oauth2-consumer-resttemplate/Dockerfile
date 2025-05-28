FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/sts-oauth2-consumer-resttemplate-0.0.1-SNAPSHOT.jar sts-oauth2-consumer-resttemplate.jar
COPY target/sts-oauth2-consumer-resttemplate-0.0.1-SNAPSHOT.jar sts-oauth2-consumer-resttemplate-0.0.1-SNAPSHOT.jar
EXPOSE 7774
ENTRYPOINT ["java", "-jar", "sts-oauth2-consumer-resttemplate-0.0.1-SNAPSHOT.jar"]