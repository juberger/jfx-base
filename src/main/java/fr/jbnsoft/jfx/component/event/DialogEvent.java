package fr.jbnsoft.jfx.component.event;

public class DialogEvent {

	// ------------------------------------------------
	// - Constants
	// ------------------------------------------------

	public static final String ACTION_VALIDATE = "validate";
	public static final String ACTION_CLOSE = "close";
	
	// ------------------------------------------------
	// - Variables
	// ------------------------------------------------

	private String action;
	
	private Object data;
	
	// ------------------------------------------------
	// - Methods
	// ------------------------------------------------
	
	public DialogEvent(String action, Object data) {
		super();
		this.action = action;
		this.data = data;
	}
	
	// ------------------------------------------------
	// - Internal methods
	// ------------------------------------------------

	// ------------------------------------------------
	// - Getter / Setter
	// ------------------------------------------------
	
	public String getAction() {
		return action;
	}

	public Object getData() {
		return data;
	}
	
}
