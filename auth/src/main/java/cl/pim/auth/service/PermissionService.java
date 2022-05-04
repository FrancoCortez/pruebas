package cl.pim.auth.service;

import cl.pim.auth.model.Permission;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PermissionService {
    Mono<Permission> create(Permission toModel);

    Mono<Permission> update(Permission entity);

    Mono<Void> deleteById(String id);

    Mono<Void> deleteMassiveIds(List<String> ids);

    Flux<Permission> findAll();

    Mono<Permission> findById(String id);

    Flux<Permission> findByRoleId(String roleId);

    Flux<Permission> findPermissionNotByRoleId(String roleId);
}
