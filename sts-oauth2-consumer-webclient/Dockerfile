FROM openjdk:21
WORKDIR /var/lib/jenkins/workspace/jars
ADD target/sts-oauth2-consumer-webclient-0.0.1-SNAPSHOT.jar sts-oauth2-consumer-webclient.jar
COPY target/sts-oauth2-consumer-webclient-0.0.1-SNAPSHOT.jar sts-oauth2-consumer-webclient-0.0.1-SNAPSHOT.jar
EXPOSE 7773
ENTRYPOINT ["java", "-jar", "sts-oauth2-consumer-webclient-0.0.1-SNAPSHOT.jar"]