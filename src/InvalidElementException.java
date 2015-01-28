/**
* This exception signifies an invalid set element.
*/
@SuppressWarnings("serial")
public class InvalidElementException extends RuntimeException {
    public InvalidElementException() {
    }
    public InvalidElementException(String message) {
        super(message);
    }
}
