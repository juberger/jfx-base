package fr.jbnsoft.jfx.view;

import fr.jbnsoft.jfx.component.AbstractComponent;
import fr.jbnsoft.jfx.exception.JfxException;
import javafx.scene.Scene;

public interface IView {

	/**
	 * Create the {@link Scene} from this view. The Scene can be created from scratch with JavaFX graphics components or using an {@link AbstractComponent} with
	 * the buildScene method of the {@link AbstractView}.
	 * @throws JfxException
	 */
	public void createScene() throws JfxException;
	
	public void onStart();
	
}