package exceptions.operation;

import exceptions.CalculatorException;

public class OperationException extends CalculatorException {
    public OperationException(String message) {
        super(message);
    }

    public OperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
