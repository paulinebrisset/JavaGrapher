package Calculator.exception;

public class SyntaxeErrorException extends Exception {
    /**
     * Define a custom exception class SyntaxeErrorException that extends the
     * Exception class
     */
    private static final long serialVersionUID = 1L;

    public SyntaxeErrorException() {
        super();
    }

    public SyntaxeErrorException(String message) {
        super(message);
    }
}
