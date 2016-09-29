FROM openjdk:8-jre-alpine
COPY continuous-delivery-demo*.jar /home/continuous-delivery-demo.jar
WORKDIR /home
EXPOSE 8080-8080
ENTRYPOINT ["java","-Xmx200m","-jar","/home/continuous-delivery-demo.jar"]
