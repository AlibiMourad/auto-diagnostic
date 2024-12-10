# Utiliser une image Java 21
FROM openjdk:21-jdk-slim
LABEL authors="alibiMourad"

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier JAR
COPY target/auto-diagnostic-1.0-SNAPSHOT.jar app.jar

# Exécuter l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
