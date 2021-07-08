package fr.jbnsoft.jfx.controller;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;

import fr.jbnsoft.jfx.component.AbstractComponent;
import fr.jbnsoft.jfx.component.ErrorComponent;
import fr.jbnsoft.jfx.exception.JfxException;
import fr.jbnsoft.jfx.service.ComponentService;
import javafx.fxml.FXML;
import javafx.scene.Node;

public abstract class AbstractController<N extends Node> {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	ComponentService componentService;
	
	@FXML private N rootNode;
	
	private AbstractController<?> parent;

	private ErrorComponent errorComponent;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	/**
	 * Method to initialize the content of the panel associated to the controller
	 */
	public abstract void onInit();
	
	public void onStart() {};
	
	public void createErrorComponent(String title, String details) {};

	public <T extends AbstractComponent<?, ?>> T loadComponent(Class<T> componentType) throws JfxException {
		T component = componentService.load(componentType);
		if (component.getController() != null) {
			component.getController().onInit();
		}
		return component;
	}

	public <T extends AbstractComponent<?, ?>> T loadComponent(URL fxml, Class<T> componentType) throws JfxException {
		T component = componentService.load(fxml, componentType);
		if (component.getController() != null) {
			component.getController().onInit();
		}
		return component;
	}

	public <T extends AbstractComponent<?, ?>> T loadComponent(String fxml, Class<T> componentType) throws JfxException {
		T component = componentService.load(fxml, componentType);
		if (component.getController() != null) {
			component.getController().onInit();
		}
		return component;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	
	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

	public AbstractController<?> getParent() {
		return parent;
	}

	public void setParent(AbstractController<?> parent) {
		this.parent = parent;
	}

	public ErrorComponent getErrorComponent() {
		return errorComponent;
	}

	public void setErrorComponent(ErrorComponent errorComponent) {
		this.errorComponent = errorComponent;
	}

	public N getRootNode() {
		return rootNode;
	}

	public void setRootNode(N rootNode) {
		this.rootNode = rootNode;
	}
	
}