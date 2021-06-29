package fr.jbnsoft.jfx.component;

import fr.jbnsoft.jfx.controller.AbstractController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class ErrorComponent {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------
	
	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------
	
	private String title;
	private String details;
	private AbstractController owner;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public ErrorComponent(String title, String details, AbstractController owner) {
		this.title = title;
		this.details = details;
		this.owner = owner;
	}
	
	public boolean displayError() {
		boolean displayed = false;
		
		if (title != null && !title.isEmpty() && details != null && !details.isEmpty() && owner != null) {
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