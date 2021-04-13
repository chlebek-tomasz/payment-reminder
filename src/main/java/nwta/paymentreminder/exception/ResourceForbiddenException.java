package nwta.paymentreminder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serializable;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class ResourceForbiddenException extends RuntimeException {

    private static final long serialVersionUID = 5898275100781206613L;

    public ResourceForbiddenException() { super("Resource forbidden"); }

    public ResourceForbiddenException(String message) {
        super(message);
    }
}
