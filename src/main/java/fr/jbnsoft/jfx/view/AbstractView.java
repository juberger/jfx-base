package fr.jbnsoft.jfx.view;

import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import fr.jbnsoft.jfx.JfxApplication;
import fr.jbnsoft.jfx.controller.AbstractController;
import fr.jbnsoft.jfx.controller.AppFXMLLoader;
import fr.jbnsoft.jfx.exception.JfxException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public abstract class AbstractView implements IView {
	
	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	private AppFXMLLoader appFXMLLoader;
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
			viewController.onStrart();
		}
	}

	public FXMLLoader getLoader(URL url) throws JfxException {
		return appFXMLLoader.getLoader(url);
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

	public JfxApplication getApplication() {
		return application;
	}

	public void setApplication(JfxApplication application) {
		this.application = application;
	}

	public AppFXMLLoader getAppFXMLLoader() {
		return appFXMLLoader;
	}

	public void setAppFXMLLoader(AppFXMLLoader appFXMLLoader) {
		this.appFXMLLoader = appFXMLLoader;
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