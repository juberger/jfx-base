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
	
	protected boolean okClicked = false;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	public abstract Stage createDialog(Parent page);
	
	public Stage createDialog() {
		onStrart();
		return createDialog(this.page);
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

	public boolean isOkClicked() {
		return okClicked;
	}

	public void setOkClicked(boolean okClicked) {
		this.okClicked = okClicked;
	}

}