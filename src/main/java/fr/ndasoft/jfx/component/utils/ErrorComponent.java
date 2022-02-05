package fr.ndasoft.jfx.component.utils;

import fr.ndasoft.jfx.component.IJfxComponent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorComponent implements IJfxComponent {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------
	
	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------
	
	private String title;
	private String details;
	private IJfxComponent parent;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public ErrorComponent(String title, String details, IJfxComponent parent) {
		this.title = title;
		this.details = details;
		this.parent = parent;
	}
	
	public boolean displayError() {
		boolean displayed = false;
		
		if (title != null && !title.isEmpty() && details != null && !details.isEmpty() && parent != null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error message");
			alert.setHeaderText(title);
			alert.setContentText(details);
			alert.showAndWait();
			displayed = true;
		}
		
		return displayed;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
}