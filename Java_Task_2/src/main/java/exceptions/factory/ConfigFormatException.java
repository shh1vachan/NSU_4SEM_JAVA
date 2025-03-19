package exceptions.factory;

public class ConfigFormatException extends FactoryException {
    public ConfigFormatException(String message) {
        super(message);
    }

    public ConfigFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
