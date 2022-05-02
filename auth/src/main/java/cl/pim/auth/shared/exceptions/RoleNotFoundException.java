package cl.pim.auth.shared.exceptions;


public class RoleNotFoundException extends NotFoundException {

    public RoleNotFoundException(String id) {
        super(String.format("Item [%d] is not found", id));
    }

}
