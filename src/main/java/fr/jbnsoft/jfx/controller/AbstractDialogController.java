package fr.jbnsoft.jfx.controller;

import fr.jbnsoft.jfx.component.event.DialogEvent;
import fr.jbnsoft.jfx.component.event.DialogListener;
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
	
	private DialogListener dialogListener;
	
	private Object data = null;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	public abstract void onOpenDialog();
	
	/**
	 * Close dialog with validate flag to false <br/>
	 * Call the DialogListener if not null
	 * @return the validate flag
	 */
	public boolean cancelClose() {
		dialogStage.close();
		validate = false;
		if (dialogListener != null) {
			dialogListener.onClose(new DialogEvent(DialogEvent.ACTION_CLOSE, data));
		}
		return validate;
	}
	
	/**
	 * Close dialog with validate flag to true <br/>
	 * Call the DialogListener if not null
	 * @return the validate flag
	 */
	public boolean valideClose() {
		dialogStage.close();
		validate = true;
		if (dialogListener != null) {
			dialogListener.onClose(new DialogEvent(DialogEvent.ACTION_VALIDATE, data));
		}
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

	public DialogListener getDialogListener() {
		return dialogListener;
	}

	public void setDialogListener(DialogListener dialogListener) {
		this.dialogListener = dialogListener;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}