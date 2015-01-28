/**
* This exception signifies an invalid set name.
*/
@SuppressWarnings("serial")
public class InvalidSetNameException extends RuntimeException {
    public InvalidSetNameException() {
    }
    public InvalidSetNameException(String message) {
        super(message);
    }
}
