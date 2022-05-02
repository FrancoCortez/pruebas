package cl.pim.auth.repository;

import cl.pim.auth.model.RolePermissionRelation;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface RolePermissionRelationRepository extends ReactiveCrudRepository<RolePermissionRelation, String> {

    Mono<Void> deleteByRoleId(String roleId);

    @Query("delete from role_permission_relation where role_id in (:idsRoles)")
    Mono<Void> deleteAllByRoleId(List<String> idsRoles);
}
