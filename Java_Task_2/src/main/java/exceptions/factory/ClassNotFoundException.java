package exceptions.factory;

public class ClassNotFoundException extends FactoryException {
    public ClassNotFoundException(String message) {
        super(message);
    }
}
