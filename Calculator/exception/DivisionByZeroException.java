package Calculator.exception;

public class DivisionByZeroException extends Exception {
    private static final long serialVersionUID = 1L;

    public DivisionByZeroException() {
        super();
    }

    public DivisionByZeroException(String message) {
        super(message);
    }
}
