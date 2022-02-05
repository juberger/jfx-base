package fr.ndasoft.jfx.component;

import org.springframework.beans.factory.annotation.Autowired;

import fr.ndasoft.jfx.exception.JfxException;
import fr.ndasoft.jfx.loader.JfxFXMLLoader;
import javafx.scene.Node;

public abstract class JfxComponent<N extends Node> implements IJfxComponent {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	JfxFXMLLoader fxmlLoader;
	
	private N rootNode;
	
	private JfxComponent<?> parent;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	/**
	 * Method to initialize the content of the panel associated to the controller
	 */
	public abstract void onInit();
	
	/**
	 * Load component from controller that extend {@link JfxComponent} and fire <code>onInit</code> method
	 * @param componentType controller to load
	 * @return controller
	 * @throws JfxException
	 */
	@SuppressWarnings("rawtypes")
	public <T extends JfxComponent> T loadComponent(Class<T> componentType) throws JfxException {
		return fxmlLoader.loadComponent(componentType);
	}

	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
	public N getRootNode() {
		return rootNode;
	}
	
	public void setRootNode(N rootNode) {
		this.rootNode = rootNode;
	}

	public JfxComponent<?> getParent() {
		return parent;
	}

	public void setParent(JfxComponent<?> parent) {
		this.parent = parent;
	}

}
