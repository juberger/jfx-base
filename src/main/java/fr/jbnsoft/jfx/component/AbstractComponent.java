package fr.jbnsoft.jfx.component;

import java.io.IOException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.jbnsoft.jfx.controller.AbstractController;
import fr.jbnsoft.jfx.controller.AppFXMLLoader;
import fr.jbnsoft.jfx.exception.JfxException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public abstract class AbstractComponent<T extends AbstractController<N>, N extends Node> {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	private static final Logger logger = LoggerFactory.getLogger(AbstractComponent.class);

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------
	
	private URL fxml;
	
	private T controller;
	
	private AbstractComponent<?, ?> parent;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public AbstractComponent() {
	}
	
	public AbstractComponent(AbstractComponent<?, ?> parent) {
		this.parent = parent;
	}

	public AbstractComponent(URL fxml) throws IOException {
		this.fxml = fxml;
	}
	
	public AbstractComponent(AbstractComponent<?, ?> parent, URL fxml) throws IOException {
		this(fxml);
		this.parent = parent;
	}

	public void load(AppFXMLLoader appFXMLLoader) throws IOException {
		if (fxml != null && appFXMLLoader != null) {
			try {
				FXMLLoader fxmlLoader = appFXMLLoader.getLoader(fxml);
				N root = fxmlLoader.load();
				controller = fxmlLoader.getController();
				controller.setRootNode(root);
			} catch (JfxException e) {
				logger.error("Unabled to load FXML " + fxml);
				throw new IOException("Unabled to load FXML " + fxml);
			}
		}
	}

	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
	public URL getFxml() {
		return fxml;
	}
	
	public void setFxml(URL fxml) {
		this.fxml = fxml;
	}
	
	public T getController() {
		return controller;
	}
	
	public void setController(T controller) {
		this.controller = controller;
	}

	public N getRootNode() {
		return controller.getRootNode();
	}

	public AbstractComponent<?, ?> getParent() {
		return parent;
	}

	public void setParent(AbstractComponent<?, ?> parent) {
		this.parent = parent;
	}

}
