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
    <groupId>fr.ndasoft.library</groupId>
    <artifactId>jfx-base</artifactId>
    <version>1.0.2</version>
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
	public JfxFXMLLoader getAppFXMLLoader() {
		return new JfxFXMLLoader();
	}

}
```

## 3 - Classes du projet

Une fois votre projet initialisé et configuré, vous pouvez commencer à créer les classes pour les différentes parties.

### 3.1 - Main classe

La main classe du projet doit étendre *fr.ndasoft.jfx.JfxApplication* et être annotée *@SpringBootApplication*.

```java
package fr.monprojet.monprojetjfx;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.ndasoft.jfx.JfxApplication;
import javafx.stage.Stage;

@SpringBootApplication
public class MonProjetJfx extends JfxApplication {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // TODO Auto-generated method stub

    }

}
```

Ajouter la méthode *main* pour lancer l'application JavaFX et la méthode *init* pour lancer l'application Spring Boot.

La méthode *init* set le context Spring une fois l'application Spring Boot démarré.

```java
public static void main(String[] args) {
    launch(MonProjetJfx.class, args);
}

@Override
public void init() throws Exception {
    context = SpringApplication.run(MonProjetJfx.class);
}
```

Afficher la fenêtre principal de l'application avec le stage.

```java
@Override
public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("Mon Projet Jfx");
    primaryStage.setMinWidth(1280);
    primaryStage.setMinHeight(800);
    primaryStage.setWidth(1280);
    primaryStage.setHeight(800);
    primaryStage.show();
}
```

## 3.2 La view

Afin de regrouper tout ce qui concerne un écran de l'application, la librairie se base sur un classe view qui étend la classe *fr.ndasoft.jfx.view.AbstractView*. Cette classe permet de créer la *Scene* qui sera ajoutée au *Stage* principal.

Il faut annoter cette classe *@Component* afin qu'elle soit intégrée au contexte *Spring*

```java
package fr.monprojet.monprojetjfx.view;

import fr.ndasoft.jfx.exception.JfxException;
import fr.ndasoft.jfx.view.AbstractVie

@Component
public class MainView extends AbstractView {

	@Override
	public void createScene() throws JfxException {
		// TODO Auto-generated method stub
		
	}

}
```

Dans cette classe il faut implémenter la méthode *createScene()* afin d'y ajouter les composants JavaFX qui composeront notre *Scene*.

Pour cela il y a deux possibilités : 

- soit créer les composants programatiquement et les ajouter dans une nouvelle *Scene* via la méthode *setScene(...)*

- soit créer un *Controller* et son fichier *FXML* assossié puis utiliser la méthode *buildScene(...)* fourni par la librairie

Ici nous n'aborderons que la deuxième possibilité.

## 3.3 Un composant

Dans la librairie un composant est constitué d'un fichier *FXML* et d'une classe *Controller* qui sont utilisé par JavaFX pour construire et animer un composant graphique.

Un fichier *FXML* détaillant le composition du composant et faisant le lien avec le *Controller*. Ce fichier est placé dans la partie *src/main/resources* du projet dans le chemin *fxml/main/MainPanel.xml*

<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>

<?import javafx.scene.control.Button?>

<?import javafx.scene.control.TextArea?>

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.TextArea?>
<?import javafx.scene.control.TextField?>
<?import javafx.scene.layout.BorderPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.VBox?>


<BorderPane xmlns:fx="http://javafx.com/fxml/1" xmlns="http://javafx.com/javafx/16" fx:controller="fr.monprojet.monprojetjfx.view.controller.MainController">
   <center>
      <VBox BorderPane.alignment="CENTER">
         <children>
            <HBox alignment="CENTER_LEFT" prefHeight="40.0" prefWidth="200.0">
               <children>
                  <TextField fx:id="inputVal" />
                  <Button fx:id="btVal" mnemonicParsing="false" onAction="#onClickVal" prefHeight="25.0" prefWidth="61.0" text="Valider" />
               </children>
            </HBox>
            <HBox prefHeight="100.0" prefWidth="200.0">
               <children>
                  <TextArea fx:id="outputVal" prefHeight="200.0" prefWidth="200.0" />
               </children>
            </HBox>
         </children>
         <opaqueInsets>
            <Insets />
         </opaqueInsets>
         <padding>
            <Insets bottom="10.0" left="10.0" right="10.0" top="10.0" />
         </padding>
      </VBox>
   </center>
</BorderPane>

```

Un *Controller* qui va permettre de coder la logioque du composant.

```java
package fr.monprojet.monprojetjfx.view.controller;

import fr.ndasoft.jfx.component.JfxComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController extends JfxComponent<BorderPane> {
	
	@FXML TextField inputVal;
	@FXML Button btVal;
	@FXML TextArea outputVal;

	@Override
	public void onInit() {
		// TODO Auto-generated method stub
		
	}
	
	@FXML public void onClickVal() {
		
	}

}
```

Le *Controller* étend la classe *JfxComponent* qui permet d'indiquer à la librairie qu'il s'agit d'un composant graphique.

La méthode *onInit()* permet de préparer le composant lorsqu'il est créé par la librairie, on peut par exemple initialisé une valeur par défaut dans le *TextField*.

Il faut maintenant ajouter deux annotations, la première *@Component* pour qui soit pris en compte par le contexte Spring, et la deuxième @FxmlUrl pour que la librairie charge le fichier *FXML* assossié au *Controller*.

```java
@Component
@FxmlUrl(url = "fxml/main/MainPanel.xml")
public class MainController extends JfxComponent<BorderPane> {
...
}
```

## 3.4 Chargement du composant dans la view

Maintenant que nous avons défini notre composant nous pouvons nous en servire comme composant qui sera affiché dans la main view de notre application.

Pour cela nous reprenons notre classe MainView et nous allons utiliser la méthode buildScene(...) pour créer la Scene principal de notre view à partir de notre composant.

```java
@Component
public class MainView extends AbstractView {

	@Override
	public void createScene() throws JfxException {
		buildScene(MainController.class);
	}

}
```

Ensuite il faut ajouter la *Scene* de notre view dans le *Stage* principal de l'application.

```java
@SpringBootApplication
public class MonProjetJfx extends JfxApplication {
	
	...

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Mon Projet Jfx");
	    primaryStage.setMinWidth(1280);
	    primaryStage.setMinHeight(800);
	    primaryStage.setWidth(1280);
	    primaryStage.setHeight(800);
	    primaryStage.setScene(getScene(MainView.class));
	    primaryStage.show();
	}

}
```

La méthode *getScene(...)* de la librairie va récupérer l'objet *MainView* dans le contexte *String* et faire appel à la méthode *getScene(...)* de cette dernière afin de récupérer la *Scene* créée par la méthode *createScene(...)* de la view.

## 3.5 Chargement d'un composant dans un autre composant

Il est possible de charger un composant dans un autre composant afin de réutiliser un composant transverse ou de décomposer un écran un peu complexe en sous composants.

Pour cela la classe *JfxComponent* propose la méthode *loadComponent(...)* qui charge le *Controller* et le fichier *FXML* assossié et retourne le *Controller*.

```java
@Component
@FxmlUrl(url = "fxml/main/OtherPanel.fxml")
public class OtherController extends JfxComponent<BorderPane> {
	
	MainController mainController;

	@Override
	public void onInit() {
		try {
			mainController = loadComponent(MainController.class);
			getRootNode().setCenter(mainController.getRootNode());
		} catch (JfxException e) {
			e.printStackTrace();
		}
		
	}

}
```

Ici nous avons un composant *OtherController* qui va charger dans sa méthode *onInit(...)* le composant *MainController* et ajouter le root node de ce dernier dans le centre de sont *BorderPane* qui est son propre root node.
