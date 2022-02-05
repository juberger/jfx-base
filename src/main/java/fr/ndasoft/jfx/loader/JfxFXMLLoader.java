package fr.ndasoft.jfx.loader;

import java.io.IOException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import fr.ndasoft.jfx.exception.JfxException;
import fr.ndasoft.jfx.annotation.FxmlUrl;
import fr.ndasoft.jfx.component.JfxComponent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

@Component
public class JfxFXMLLoader {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	private ConfigurableApplicationContext context;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public FXMLLoader getLoader(URL url) throws JfxException {
		if (url == null) {
			throw new JfxException("The URL representing the FXML file path mustn't be null");
		}
		FXMLLoader loader = new FXMLLoader(url);
		loader.setControllerFactory(context::getBean);
		return loader;
	}
	
	public FXMLLoader getLoader(String fxmlPath) throws JfxException {
		return getLoader(getClass().getResource(fxmlPath));
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T extends JfxComponent> T loadComponent(Class<T> componentType) throws JfxException {
		if (!componentType.isAnnotationPresent(FxmlUrl.class)) {
			throw new JfxException("The FxmlUrl annotation is required to load component " + componentType.toString());
		}
		if (componentType.getAnnotation(FxmlUrl.class).url().isEmpty()) {
			throw new JfxException("The url attribut of FxmlUrl annotation is required to load component " + componentType.toString());
		}
		String url = componentType.getAnnotation(FxmlUrl.class).url();
		URL fxmlUrl = componentType.getClassLoader().getResource(url);
		if (fxmlUrl == null) {
			throw new JfxException("Fxml file not found");
		}
		FXMLLoader fxmlLoader = getLoader(fxmlUrl);
		Node root;
		try {
			root = fxmlLoader.load();
		} catch (IOException e) {
			throw new JfxException("Unable to load fxml", e);
		}
		T controller = fxmlLoader.getController();
		if (controller != null) {
			controller.setRootNode(root);
			controller.onInit();
		}
		return controller;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------
	
	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

}