# jfx-base JavaFX/Spring Boot lib

Ensemble de classes fournissant un socle de base pour la création d'application JavaFX / Spring boot.

## 1 - Initialisation d'un projet

Pour initialiser votre projet vous avez deux solutions, soit créer un projet maven et y ajouter les librairies nécessaires, soit partie du pom.xml fourni ici.

### 1.1 - Création d'un projet maven

Pour créer un projet maven de zéro il vous faut y ajouter la liste des dépendances suivante :

- org.springframework.boot/spring-boot-starter

- javax.inject/javax.inject

- org.openjfx/javafx-controls

- org.openjfx/javafx-fxml

Vous devez également ajouter le parent de spring-boot-starter :

- org.springframework.boot/spring-boot-starter-parent

Et pour finir il vous faut ajouter la dépendance vers la lib jfx-base :

```xml
<dependency>
	<groupId>fr.jbnsoft.library</groupId>
	<artifactId>jfx-base</artifactId>
	<version>0.0.3</version>
</dependency>
```

### 1.2 - Créer un projet à partir du pom.xml

Vous pouvez récupérer le **pom.xml** prés défini suivant :

https://github.com/juberger/jfx-base/tree/main/src/config/pom.xml

Puis configurer le **GAV** avec les informations de votre projet et mettre à jour les versions des différentes dépendances si nécessaire.

## 2 - Configuration du projet

### 2.1 - Configuration des beans

Afin de pouvoir utiliser le chargement des composants via le context Spring i lfaut ajouter la configuration suivante à votre projet :

```java
@Configuration
public class AppConfiguration {
	
	@Bean
	public AppFXMLLoader getAppFXMLLoader() {
		return new AppFXMLLoader();
	}

	@Bean
	public ComponentService getComponentService() {
		return new ComponentService();
	}

}

```
