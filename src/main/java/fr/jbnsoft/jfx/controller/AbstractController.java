package fr.jbnsoft.jfx.controller;

import fr.jbnsoft.jfx.component.ErrorComponent;
import javafx.scene.Node;

public abstract class AbstractController<N extends Node> {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------
	
//	@Autowired
//	protected AppFXMLLoader appFXMLLoader;
	
	private N rootNode;
	
	private AbstractController<?> parent;

	private ErrorComponent errorComponent;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	public abstract void onStrart();
	
	public void createErrorComponent(String title, String details) {};
	
//	protected AbstractDialogController loadDialog(String path) throws IOException, JfxException {
//		FXMLLoader fxmlLoader = appFXMLLoader.getLoader(path);
//		fxmlLoader.load();
//		AbstractDialogController dialogController = fxmlLoader.getController();
//		dialogController.setParent(this);
//		
//		return dialogController;
//	}

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