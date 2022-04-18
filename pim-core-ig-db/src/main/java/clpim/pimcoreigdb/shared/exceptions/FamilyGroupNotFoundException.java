package clpim.pimcoreigdb.shared.exceptions;


public class FamilyGroupNotFoundException extends NotFoundException {

    public FamilyGroupNotFoundException(String id) {
        super(String.format("Item [%d] is not found", id));
    }

}
