package fr.ndasoft.jfx.component;

import fr.ndasoft.jfx.component.event.DialogEvent;
import fr.ndasoft.jfx.component.event.DialogListener;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class JfxComponentDialog<N extends Node> extends JfxComponent<N> {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	protected Stage dialogStage;

	protected Stage parentStage;

	private DialogListener dialogListener;
	
	private Object data = null;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------

	/**
	 * Override this method to implement some action on dialog open
	 */
	public abstract void onDialogOpen();
	
	/**
	 * Override this method to implement some action on dialog close
	 */
	public abstract void onDialogClose();
	
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
		return dialogStage;

	}

	public void openDialog() {
		openDialog(null, null, null);
	}

	public void openDialog(DialogListener dialogListener) {
		openDialog(dialogListener, null, null);
	}

	public void openDialog(DialogListener dialogListener, Stage parent) {
		openDialog(dialogListener, parent, null);
	}

	public void openDialog(DialogListener dialogListener, Modality modality) {
		openDialog(dialogListener, null, modality);
	}

	public void openDialog(Stage parent, Modality modality) {
		openDialog(null, parent, modality);
	}
	
	public void openDialog(DialogListener dialogListener, Stage parent, Modality modality) {
		if (dialogStage == null) {
			createDialog(parent, modality);
		}
		this.dialogListener = dialogListener;
		onDialogOpen();
		dialogStage.showAndWait();
	}
	
	/**
	 * Action to close dialog. Call <code>onClose()</code> method and fire {@link DialogListener} if not null
	 */
	public void actionClose() {
		onDialogClose();
		dialogStage.close();
		if (dialogListener != null) {
			dialogListener.onClose(new DialogEvent(DialogEvent.ACTION_CLOSE, data));
		}
	}
	
	/**
	 * Action to close dialog on cancel event, for example when the user click on cancel button.
	 * </br>Call <code>onClose()</code> method and fire {@link DialogListener} if not null with <code>DialogEvent.ACTION_CANCEL</code>
	 */
	public void actionCancel() {
		actionCancel(null);
	}
	
	/**
	 * Action to close dialog on cancel event, for example when the user click on cancel button.
	 * </br>Call <code>onClose()</code> method and fire {@link DialogListener} if not null with <code>DialogEvent.ACTION_CANCEL</code>
	 * @param data object send by {@link DialogListener}
	 */
	public void actionCancel(Object data) {
		onDialogClose();
		dialogStage.close();
		if (dialogListener != null) {
			dialogListener.onClose(new DialogEvent(DialogEvent.ACTION_CANCEL, data));
		}
	}

	/**
	 * Action to close dialog on validate event, for example when the user click on ok button.
	 * </br>Call <code>onClose()</code> method and fire {@link DialogListener} if not null with <code>DialogEvent.ACTION_VALIDATE</code>
	 */
	public void actionValide() {
		actionValide(null);
	}

	/**
	 * Action to close dialog on validate event, for example when the user click on ok button.
	 * </br>Call <code>onClose()</code> method and fire {@link DialogListener} if not null with <code>DialogEvent.ACTION_VALIDATE</code>
	 * @param data object send by {@link DialogListener}
	 */
	public void actionValide(Object data) {
		onDialogClose();
		dialogStage.close();
		if (dialogListener != null) {
			dialogListener.onClose(new DialogEvent(DialogEvent.ACTION_VALIDATE, data));
		}
	}

	/**
	 * Action to close dialog on custom event, for example when the user click on other button than ok/cancel.
	 * </br>Call <code>onClose()</code> method and fire {@link DialogListener} if not null with <code>actionType</code>
	 * @param actionType custom String action send by {@link DialogListener}
	 * @param data object send by {@link DialogListener}
	 */
	public void actionCustom(String actionType) {
		actionCustom(actionType, null);
	}

	/**
	 * Action to close dialog on custom event, for example when the user click on other button than ok/cancel.
	 * </br>Call <code>onClose()</code> method and fire {@link DialogListener} if not null with <code>actionType</code>
	 * @param actionType custom String action send by {@link DialogListener}
	 * @param data object send by {@link DialogListener}
	 */
	public void actionCustom(String actionType, Object data) {
		onDialogClose();
		dialogStage.close();
		if (dialogListener != null) {
			dialogListener.onClose(new DialogEvent(actionType, data));
		}
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

}
