FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE
COPY target/*.jar simplerest.jar
ENV _JAVA_OPTIONS "-Xms256m -Xmx512m -Djava.awt.headless=true"
RUN addgroup restapp && \
    adduser -D -S -h /var/cache/restapp -s /sbin/nologin -G restapp restapp
ENTRYPOINT ["java","-jar","/simplerest.jar"]
EXPOSE 7070/tcp
