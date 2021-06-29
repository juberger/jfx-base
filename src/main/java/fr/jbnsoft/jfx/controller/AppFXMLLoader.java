package fr.jbnsoft.jfx.controller;

import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import fr.jbnsoft.jfx.exception.JfxException;
import javafx.fxml.FXMLLoader;

@Component
public class AppFXMLLoader {

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
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------
	
	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

}