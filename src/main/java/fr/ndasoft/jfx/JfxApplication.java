package fr.ndasoft.jfx;

import org.springframework.context.ConfigurableApplicationContext;

import fr.ndasoft.jfx.view.AbstractView;
import javafx.application.Application;
import javafx.scene.Scene;

public abstract class JfxApplication extends Application {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	protected ConfigurableApplicationContext context;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	@Override
	public void stop() throws Exception {
		context.close();
	}

	public <T extends AbstractView> T getView(Class<T> type) {
		if (context != null) {
			return context.getBean(type);
		}
		return null;
	}
	
	public <T extends AbstractView> Scene getScene(Class<T> viewType) {
		if (context != null) {
			AbstractView view = getView(viewType);
			if (view != null) {
				return view.getScene();
			}
		}
		return null;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

	public ConfigurableApplicationContext getContext() {
		return context;
	}

}
