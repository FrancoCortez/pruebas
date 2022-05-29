package cl.pim.auth.shared.exceptions;

import org.springframework.http.HttpStatus;

public class ValidationException extends GlobalException {
    public ValidationException(String message) {
        super(HttpStatus.BAD_REQUEST, message);
    }
}
