FROM java:openjdk-8u91
ADD ./build/libs/api-client-server.jar ./api-client-server.jar
CMD ["java", "-Xmx200m", "-jar", "./api-client-server.jar"]

EXPOSE 80