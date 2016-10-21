FROM java:openjdk-8u91
ADD ./build/libs/auth-service.jar ./auth-service.jar
CMD ["java", "-Xmx200m", "-jar", "./auth-service.jar"]

EXPOSE 5000