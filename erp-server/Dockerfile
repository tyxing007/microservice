FROM java:openjdk-8u91
ADD ./build/libs/erp-server.jar ./erp-server.jar
CMD ["java", "-Xmx200m", "-jar", "./erp-server.jar"]

EXPOSE 8000