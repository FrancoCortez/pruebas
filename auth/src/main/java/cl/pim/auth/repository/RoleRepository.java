package cl.pim.auth.repository;

import cl.pim.auth.model.Role;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface RoleRepository extends ReactiveCrudRepository<Role, String> {

    @Query("select r.* from role r " +
            "inner join user_role_relation urr on r.id = urr.role_id " +
            "where urr.user_id = :userId")
    Flux<Role> findAllByUserId(String userId);
}
