FROM java:openjdk-8u91
ADD ./build/libs/config.jar ./config.jar
CMD ["java", "-Xmx200m", "-jar", "./config.jar"]

EXPOSE 8888