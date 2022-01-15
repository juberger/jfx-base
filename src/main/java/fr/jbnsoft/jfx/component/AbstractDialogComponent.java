package fr.jbnsoft.jfx.component;

import java.io.IOException;
import java.net.URL;

import fr.jbnsoft.jfx.component.event.DialogListener;
import fr.jbnsoft.jfx.controller.AbstractDialogController;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class AbstractDialogComponent<T extends AbstractDialogController<N>, N extends Node> extends AbstractComponent<T, N> {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	protected Stage dialogStage;

	protected Stage parentStage;

	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	public AbstractDialogComponent() {
		super();
	}
	
	public AbstractDialogComponent(URL fxml) throws IOException {
		super(fxml);
	}

	/**
	 * Override this method to add configuration on the Stage of the dialog, 
	 * like title, resizable, etc...
	 * @param dialogStage instanciated Stage of the dialog
	 */
	protected void configureDialog(Stage dialogStage) {
	}

	public Stage createDialog() {
		return createDialog(null, null);
	}

	public Stage createDialog(Stage parent) {
		return createDialog(parent, null);
	}

	public Stage createDialog(Stage parent, Modality modality) {
		dialogStage = new Stage();
		dialogStage.setScene(new Scene((Parent) getRootNode()));
		configureDialog(dialogStage);
		if (parent != null) {
			parentStage = parent;
			dialogStage.initOwner(parent);
		}
		if (modality != null) {
			dialogStage.initModality(modality);
		}
		if (getController() != null) {
			getController().setDialogStage(dialogStage);
			getController().onStart();
		}
		return dialogStage;

	}

	public void openDialog() {
		openDialog(null);
	}
	
	public void openDialog(DialogListener dialogListener) {
		if (dialogStage == null) {
			createDialog();
		}
		getController().setValidate(false);
		getController().onOpenDialog();
		getController().setDialogListener(dialogListener);
		dialogStage.showAndWait();
	}

	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
	public boolean isValidate() {
		return getController().isValidate();
	}

}
