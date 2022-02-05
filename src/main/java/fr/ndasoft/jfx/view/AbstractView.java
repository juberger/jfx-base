package fr.ndasoft.jfx.view;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fr.ndasoft.jfx.exception.JfxException;
import fr.ndasoft.jfx.component.JfxComponent;
import fr.ndasoft.jfx.loader.JfxFXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public abstract class AbstractView {
	
	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	JfxFXMLLoader fxmlLoader;
	private Scene scene;
	private JfxComponent<?> viewController;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	/**
	 * Create the {@link Scene} from this view. The Scene can be created from scratch with JavaFX graphics components or using an {@link JfxComponent} with
	 * the buildScene method of the {@link AbstractView}.
	 * @throws JfxException
	 */
	public abstract void createScene() throws JfxException;
	
	@SuppressWarnings("rawtypes")
	public <T extends JfxComponent> Scene buildScene(Class<T> componentType) throws JfxException {
		T component = fxmlLoader.loadComponent(componentType);
		setViewController(component);
		setScene(new Scene((Parent) component.getRootNode()));
		return scene;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------
	
	@PostConstruct
	private void init() throws JfxException {
		createScene();
	}
	
	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public JfxComponent<?> getViewController() {
		return viewController;
	}

	public void setViewController(JfxComponent<?> viewController) {
		this.viewController = viewController;
	}
	
}