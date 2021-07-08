package fr.jbnsoft.jfx.service;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.jbnsoft.jfx.component.AbstractComponent;
import fr.jbnsoft.jfx.controller.AppFXMLLoader;
import fr.jbnsoft.jfx.exception.JfxException;

@Service
public class ComponentService {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	@Autowired
	AppFXMLLoader appFXMLLoader;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public <T extends AbstractComponent<?, ?>> T load(Class<T> componentType) throws JfxException {
		try {
			Constructor<T> componentConstructor = componentType.getConstructor();
			T component = componentConstructor.newInstance();
			component.load(appFXMLLoader);
			return component;
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
			throw new JfxException("Unabled to instanciate compoenent type " + componentType.getCanonicalName(), e);
		}
	}
	
	public <T extends AbstractComponent<?, ?>> T load(URL fxml, Class<T> componentType) throws JfxException {
		try {
			Constructor<T> componentConstructor = componentType.getConstructor(URL.class);
			T component = componentConstructor.newInstance(fxml);
			component.load(appFXMLLoader);
			return component;
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
			throw new JfxException("Unabled to instanciate compoenent type " + componentType.getCanonicalName(), e);
		}
	}

	public <T extends AbstractComponent<?, ?>> T load(String fxml, Class<T> componentType) throws JfxException {
		try {
			Constructor<T> componentConstructor = componentType.getConstructor(String.class);
			T component = componentConstructor.newInstance(fxml);
			component.load(appFXMLLoader);
			return component;
		} catch (NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException | InstantiationException | IllegalAccessException | IOException e) {
			throw new JfxException("Unabled to instanciate compoenent type " + componentType.getCanonicalName(), e);
		}
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
}
