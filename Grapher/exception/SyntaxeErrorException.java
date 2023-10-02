package Grapher.exception;

public class SyntaxeErrorException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SyntaxeErrorException() {
        super();
    }

    public SyntaxeErrorException(String message) {
        super(message);
    }
}
