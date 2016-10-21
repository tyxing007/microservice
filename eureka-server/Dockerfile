FROM java:openjdk-8u91
ADD ./build/libs/eureka-server.jar ./eureka-server.jar
CMD ["java", "-Xmx200m", "-jar", "/eureka-server.jar"]

EXPOSE 8761