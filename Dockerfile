# Usamos una imagen base de Java
FROM openjdk:17-jdk-slim

# Directorio donde se copiará el jar
WORKDIR /app

# Copia el jar generado al contenedor
COPY target/peliculas-api-0.0.1-SNAPSHOT.jar app.jar

# Expone el puerto (ajústalo si tu app corre en otro)
EXPOSE 8080

# Comando para correr la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
