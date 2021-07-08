package fr.jbnsoft.jfx.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public abstract class AbstractDialogController<N extends Node> extends AbstractController<N> {
	
	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------
	
	@FXML
	protected AnchorPane page;
	
	protected Stage dialogStage;
	
	protected Stage parentStage;
	
	protected boolean okClicked = false;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	protected abstract Stage createDialog(Parent page);
	
	public Stage createDialog() {
		onStart();
		return createDialog(this.page);
	}
	
	public Stage createDialog(Stage parentStage) {
		this.parentStage = parentStage;
		Stage stage = createDialog();
		stage.initOwner(parentStage);
		return stage;
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

	public Stage getParentStage() {
		return parentStage;
	}

	public void setParentStage(Stage parentStage) {
		this.parentStage = parentStage;
	}

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

}