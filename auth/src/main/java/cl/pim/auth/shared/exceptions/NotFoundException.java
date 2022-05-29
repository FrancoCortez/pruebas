package cl.pim.auth.shared.exceptions;

import org.springframework.http.HttpStatus;

public abstract class NotFoundException extends GlobalException {
    NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
