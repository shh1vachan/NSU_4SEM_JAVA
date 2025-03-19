package exceptions.operation;

public class UnknownCommandException extends OperationException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
