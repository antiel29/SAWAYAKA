#Frontend
FROM node:18-alpine AS frontend

WORKDIR /app/Frontend/SAWAYAKA

COPY /Frontend/SAWAYAKA/package.json .
COPY /Frontend/SAWAYAKA/package-lock.json .

RUN npm install

COPY /Frontend/SAWAYAKA .

#Backend
FROM openjdk:21-slim AS backend

WORKDIR /app/Backend/SAWAYAKA

COPY /Backend/SAWAYAKA/target/SAWAYAKA-1.0-SNAPSHOT.jar .

# Main Image (java and node installed)
FROM  timbru31/java-node:21-18

WORKDIR /app


COPY --from=frontend /app/Frontend/SAWAYAKA /app/Frontend/SAWAYAKA
COPY --from=backend /app/Backend/SAWAYAKA/SAWAYAKA-1.0-SNAPSHOT.jar /app/Backend/SAWAYAKA/SAWAYAKA-1.0-SNAPSHOT.jar

#Database
ENV SPRING_DATASOURCE_URL=jdbc:mysql://sawayakaserver.mysql.database.azure.com:3306/sawayakadb?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
ENV SPRING_DATASOURCE_USERNAME=shibahibi
ENV SPRING_DATASOURCE_PASSWORD=Antiel29
ENV SPRING_JPA_HIBERNATE_DDL-AUTO=update
ENV SERVER_PORT=7000

#For run more that one command
RUN npm install -g concurrently

EXPOSE 5173
EXPOSE 7000

CMD concurrently "java -jar /app/Backend/SAWAYAKA/SAWAYAKA-1.0-SNAPSHOT.jar" "cd /app/Frontend/SAWAYAKA && npm install && npm run dev"
