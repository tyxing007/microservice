FROM java:openjdk-8u91
ADD ./build/libs/tachometerMicroService.jar ./tachometerMicroService.jar
CMD ["java", "-Xmx200m", "-jar", "./tachometerMicroService.jar"]

EXPOSE 3780