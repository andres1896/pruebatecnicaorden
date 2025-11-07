# Etapa 1: Build con Maven
FROM maven:3.9.5-eclipse-temurin-11 AS build

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar archivos del proyecto al contenedor
COPY pom.xml .
COPY src ./src

# Construir el WAR
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final con JDK
FROM eclipse-temurin:11-jdk

WORKDIR /app

# Copiar el WAR generado desde la etapa de build
COPY --from=build /app/target/*.war app.war

# Exponer el puerto que usa Spring Boot
EXPOSE 8080

# Comando para ejecutar la app
ENTRYPOINT ["java", "-jar", "app.war"]
