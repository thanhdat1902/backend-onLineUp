FROM java:8
VOLUME /tmp
EXPOSE 8080
ADD /build/libs/com.server.onlineup-0.0.1.jar backend_server.jar
ENTRYPOINT ["java", "-Xmx1024m", "-jar","backend_server.jar"]
