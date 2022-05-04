package cl.pim.auth.repository;

import cl.pim.auth.model.Permission;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PermissionRepository extends ReactiveCrudRepository<Permission, String> {

    @Query("select p.* from permission p inner join role_permission_relation rpr on p.id = rpr.permission_id where role_id = :roleId")
    Flux<Permission> findPermissionByRoleId(String roleId);

    @Query("select p.* from permission p " +
            "where p.id not in " +
            "(select rpr.permission_id from role_permission_relation rpr where rpr.role_id = :roleId)")
    Flux<Permission> findPermissionNotByRoleId(String roleId);
}
