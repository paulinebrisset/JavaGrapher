package Calculator.exception;

public class LogByZeroException extends Exception {
    private static final long serialVersionUID = 1L;

    public LogByZeroException() {
        super();
    }

    public LogByZeroException(String message) {
        super(message);
    }
}
