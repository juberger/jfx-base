package fr.jbnsoft.jfx.view;

import fr.jbnsoft.jfx.exception.JfxException;

public interface IView {

	public AbstractView createScene() throws JfxException;
	
	public void onStart();
	
}