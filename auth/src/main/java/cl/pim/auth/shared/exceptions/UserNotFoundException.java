package cl.pim.auth.shared.exceptions;


public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String id) {
        super(String.format("Item [%s] is not found", id));
    }

}
