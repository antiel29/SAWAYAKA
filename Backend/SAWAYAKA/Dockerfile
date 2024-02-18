FROM openjdk:21-slim

ADD target/SAWAYAKA-1.0-SNAPSHOT.jar SAWAYAKA-1.0-SNAPSHOT.jar

#DB config
ENV SPRING_DATASOURCE_URL=jdbc:mysql://sawayakaserver.mysql.database.azure.com:3306/sawayakadb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=shibahibi
ENV SPRING_DATASOURCE_PASSWORD=Antiel29
ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update
ENV SERVER_PORT=7000


ENTRYPOINT [ "java","-jar","/SAWAYAKA-1.0-SNAPSHOT.jar" ]