FROM java:openjdk-8u91
ADD ./build/libs/monitoring.jar ./monitoring.jar
CMD ["java", "-Xmx200m", "-jar", "./monitoring.jar"]

EXPOSE 8989 8080