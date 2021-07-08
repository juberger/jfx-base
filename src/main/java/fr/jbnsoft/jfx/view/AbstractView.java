package fr.jbnsoft.jfx.view;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fr.jbnsoft.jfx.JfxApplication;
import fr.jbnsoft.jfx.component.AbstractComponent;
import fr.jbnsoft.jfx.controller.AbstractController;
import fr.jbnsoft.jfx.exception.JfxException;
import fr.jbnsoft.jfx.service.ComponentService;
import javafx.scene.Scene;

public abstract class AbstractView implements IView {
	
	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	ComponentService componentService;
	@Autowired
	private JfxApplication application;
	private Scene scene;
	private AbstractController<?> viewController;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	@Override
	public void onStart() {
		if (viewController != null) {
			viewController.onStart();
		}
	}

	public <T extends AbstractComponent<?, ?>> T loadComponent(Class<T> componentType) throws JfxException {
		return componentService.load(componentType);
	}

	public <T extends AbstractComponent<?, ?>> T loadComponent(URL fxml, Class<T> componentType) throws JfxException {
		return componentService.load(fxml, componentType);
	}

	public <T extends AbstractComponent<?, ?>> T loadComponent(String fxml, Class<T> componentType) throws JfxException {
		return componentService.load(fxml, componentType);
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------
	
	@PostConstruct
	private void init() throws JfxException {
		createScene();
		if (viewController != null) {
			viewController.onInit();
		}
	}
	
	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

	public JfxApplication getApplication() {
		return application;
	}

	public void setApplication(JfxApplication application) {
		this.application = application;
	}

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public AbstractController<?> getViewController() {
		return viewController;
	}

	public void setViewController(AbstractController<?> viewController) {
		this.viewController = viewController;
	}
	
}