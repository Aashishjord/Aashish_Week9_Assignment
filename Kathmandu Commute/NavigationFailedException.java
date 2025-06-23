public class NavigationFailedException extends Exception {
    public NavigationFailedException(String message) {
        super(message);
    }

    public NavigationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

