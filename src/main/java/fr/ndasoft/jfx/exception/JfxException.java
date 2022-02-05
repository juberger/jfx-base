package fr.ndasoft.jfx.exception;

public class JfxException extends Exception {
	
    // ------------------------------------------------
    // - Constants
    // ------------------------------------------------

    private static final long serialVersionUID = 1L;

    // ------------------------------------------------
    // - Variables
    // ------------------------------------------------

    private String code;

    // ------------------------------------------------
    // - Methods
    // ------------------------------------------------

    public JfxException(String message, Throwable cause) {
        super(message, cause);
    }


    public JfxException(String message, String code, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public JfxException(Throwable cause) {
        super(cause);
    }

    public JfxException(String message) {
        super(message);
    }

    public JfxException(String message, String code) {
        super(message);
        this.code = code;
    }

    // ------------------------------------------------
    // - Internal methods
    // ------------------------------------------------

    // ------------------------------------------------
    // - Getter / Setter
    // ------------------------------------------------

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}