# Utiliser une image de base avec Java 21
FROM openjdk:21-slim

# Définir les variables d'environnement
ENV APP_HOME=/app

# Créer un répertoire pour l'application
WORKDIR $APP_HOME

# Copier le fichier JAR généré par Maven dans l'image Docker
COPY target/*.jar $APP_HOME/

# Copier le fichier de configuration
COPY src/main/resources/application.properties $APP_HOME/

# Exposer le port sur lequel l'application Spring Boot écoute (par défaut 8080)
EXPOSE 8080

# Commande pour exécuter l'application
CMD ["java", "-jar", "app.jar"]
