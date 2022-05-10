package clpim.pimcoreigdb.repository;

import clpim.pimcoreigdb.model.AttributeGroup;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttributeGroupRepository extends ReactiveCrudRepository<AttributeGroup, String> {

}
