package exceptions.factory;

import exceptions.CalculatorException;

public class FactoryException extends CalculatorException {
    public FactoryException(String message) {
        super(message);
    }

    public FactoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
