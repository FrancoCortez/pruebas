package cl.pim.auth.shared.exceptions;


import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RoleNotFoundException extends NotFoundException {
    public RoleNotFoundException(String id) {
        super(String.format("Item [%s] is not found", id));
    }

}
