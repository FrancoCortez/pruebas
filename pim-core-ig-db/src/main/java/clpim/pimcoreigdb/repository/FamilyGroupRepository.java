package clpim.pimcoreigdb.repository;

import clpim.pimcoreigdb.model.FamilyGroupEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FamilyGroupRepository extends ReactiveCrudRepository<FamilyGroupEntity, String> {

}
