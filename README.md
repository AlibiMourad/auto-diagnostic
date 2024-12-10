# Auto-Diagnostic Hospitalier

## Introduction

Ce projet consiste à développer une fonctionnalité pour un **système d'information hospitalier** qui traite un **index de santé** provenant d'une cabine d'auto-diagnostic. En fonction de l'index de santé, le système détermine les pathologies du patient et les redirige vers l'unité médicale appropriée.

---

## Objectif

Le but de ce projet est de :

1. Implémenter un traitement des données issues d'un capteur d'auto-diagnostic.
2. Analyser l'index de santé, identifier les pathologies associées, et rediriger le patient vers l'unité médicale appropriée (cardiologie, traumatologie, ou les deux).
3. Déployer le système en utilisant **Maven**, **Java 21**, et **Docker**.

---

## Technologies Utilisées

1. **Java 21** : Le langage de programmation principal utilisé pour le développement.
2. **Maven** : Outil de gestion de projet pour la compilation, le test et le déploiement.
3. **Docker** : Utilisé pour la containerisation de l'application.
4. **JUnit 5** : Framework de tests unitaires pour valider les fonctionnalités.
5. **GitHub Actions** : Pour l'intégration continue (CI) et la livraison continue (CD).

---

## Architecture

1. **HealthIndexProcessor** : La classe principale qui traite l'index de santé et identifie les pathologies.
2. **Pathology Enum** : Un enum qui définit les pathologies possibles (`Cardiologie`, `Traumatologie`).
3. **Tests Unitaires** : Des tests automatisés pour valider la logique de diagnostic et les cas limites (tests d'index négatifs, multiples, etc.).

---

## Fonctionnalités

1. **Analyse de l'index de santé** :
   - Si l'index est un multiple de 3, le patient est redirigé vers **Cardiologie**.
   - Si l'index est un multiple de 5, le patient est redirigé vers **Traumatologie**.
   - Si l'index est un multiple des deux, le patient est redirigé vers les deux unités.
   - Si l'index ne correspond à aucune des conditions, aucun diagnostic n'est effectué.

2. **Tests Unitaires** :
   - Tests pour garantir la validité des indices (positifs, négatifs, zéro).
   - Tests de redirection vers les unités appropriées selon l'index de santé.

3. **Dockerisation** :
   - Le projet est containerisé avec Docker pour une facilité de déploiement.

4. **GitHub Actions** :
   - Automatisation des tests et du déploiement avec un pipeline CI/CD.

---

## Installation et Configuration

### Prérequis

1. **Java 21** installé sur votre machine. Vous pouvez le télécharger depuis [OpenJDK](https://jdk.java.net/21/).
2. **Maven** installé. Vous pouvez l'installer depuis [Apache Maven](https://maven.apache.org/).
3. **Docker** installé sur votre machine pour exécuter l'application dans un conteneur.
4. **SonarQube** (si vous souhaitez activer l'analyse de code).

### Étapes d'Installation

1. **Cloner le Projet**
   ```bash
   git clone https://github.com/username/auto-diagnostic.git
   cd auto-diagnostic
   ```

2. **Construire le Projet avec Maven**
   ```bash
   mvn clean install
   ```

3. **Exécuter les Tests**
   Pour exécuter les tests unitaires avec Maven :
   ```bash
   mvn test
   ```

4. **Lancer l'Application**
   Pour exécuter le programme principal :
   ```bash
   mvn exec:java -Dexec.mainClass="com.hospital.auto.diagnostic.HealthIndexProcessor"
   ```

5. **Créer l'Image Docker**
   Si vous souhaitez exécuter le projet dans un conteneur Docker :
    - Créez l'image Docker avec la commande suivante :
      ```bash
      mvn clean package
      docker build -t auto-diagnostic .
      ```

    - Exécutez le conteneur Docker :
      ```bash
      docker run --rm auto-diagnostic
      ```

---

## GitHub Actions (CI/CD)

Ce projet utilise **GitHub Actions** pour automatiser les tests et la génération du projet.

1. **Fichier de Workflow GitHub : `.github/workflows/maven.yml`**
   Exemple de configuration GitHub Actions pour l'intégration continue :
   ```yaml
   name: Java CI with Maven

   on:
     push:
       branches:
         - main

   jobs:
     build:
       runs-on: ubuntu-latest

       steps:
       - uses: actions/checkout@v3

       - name: Set up JDK 21
         uses: actions/setup-java@v3
         with:
           java-version: '21'
           distribution: 'temurin'

       - name: Build with Maven
         run: mvn clean install

       - name: Run Tests
         run: mvn test
   ```

2. **Exécution Automatique** :
    - Le pipeline CI est déclenché à chaque push sur la branche `main`.
    - Les tests sont exécutés automatiquement et toute modification du code est validée par des tests.

---

## Désactivation de SonarQube (Si Nécessaire)

Si SonarQube est configuré dans votre projet, vous pouvez le désactiver temporairement ou définitivement.

### A. Désactiver SonarQube dans le `pom.xml`
- Supprimez ou commentez la configuration du plugin `sonar-maven-plugin` dans le fichier `pom.xml`.

### B. Désactiver SonarQube dans GitHub Actions
- Supprimez ou commentez la section qui exécute `mvn sonar:sonar` dans le fichier de workflow GitHub (`.github/workflows/maven.yml`).

---

## Conclusion

Ce projet offre une solution complète pour l'analyse d'un index de santé dans un environnement hospitalier. Grâce à l'utilisation de **Java 21**, **Maven**, **Docker**, et **GitHub Actions**, nous avons automatisé les tests, le déploiement et l'analyse de code. La containerisation avec Docker permet de déployer l'application facilement dans différents environnements.

---

## À Propos

Ce projet a été développé par l'équipe **Diagnostic** du système d'information hospitalier. Pour toute question ou contribution, veuillez consulter la section **Issues** sur [GitHub](https://github.com/AlibiMourad/auto-diagnostic/issues).

---

### **Changelog**

- **Version 1.0** : Initialisation du projet avec la fonctionnalité de diagnostic.
- **Version 1.1** : Ajout de Docker et des tests unitaires automatisés.

---