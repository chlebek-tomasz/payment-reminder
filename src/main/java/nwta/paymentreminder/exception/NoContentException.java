package nwta.paymentreminder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends RuntimeException {

    private static final long serialVersionUID = 1587879053040378104L;

    public NoContentException() {
        super("No content");
    }

    public NoContentException(String message) {
        super(message);
    }
}
