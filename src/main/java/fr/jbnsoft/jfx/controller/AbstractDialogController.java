package fr.jbnsoft.jfx.controller;

import javafx.scene.Node;
import javafx.stage.Stage;

public abstract class AbstractDialogController<N extends Node> extends AbstractController<N> {
	
	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------
	
	protected Stage dialogStage;
	
	protected boolean validate = false;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	public abstract void onOpenDialog();
	
	/**
	 * Close dialog with validate flag to false
	 * @return the validate flag
	 */
	public boolean cancelClose() {
		dialogStage.close();
		validate = false;
		return validate;
	}
	
	/**
	 * Close dialog with validate flag to true
	 * @return the validate flag
	 */
	public boolean valideClose() {
		dialogStage.close();
		validate = true;
		return validate;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public boolean isValidate() {
		return validate;
	}

	public void setValidate(boolean validate) {
		this.validate = validate;
	}

}