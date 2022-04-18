package clpim.pimcoreigdb.repository;

import clpim.pimcoreigdb.model.FamilyEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


@Repository
public interface FamilyRepository extends ReactiveCrudRepository<FamilyEntity, String> {

    /**
     * @param familyGroupId
     * @return
     */
    @Query("select f.* from family f " +
            "inner join family_group fg on f.family_group_id = fg.id " +
            "where f.family_group_id = :family_group_id " +
            "order by f.name")
    Flux<FamilyEntity> findFamiliesByFamilyGroupId(String familyGroupId);
}
