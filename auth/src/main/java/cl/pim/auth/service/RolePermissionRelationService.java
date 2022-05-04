package cl.pim.auth.service;

import cl.pim.auth.model.RolePermissionRelation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

public interface RolePermissionRelationService {
    Mono<RolePermissionRelation> create(RolePermissionRelation item);

    Mono<Void> deleteByRoleId(String id);

    Mono<Void> deleteMassiveRoleIds(List<String> id);

    Flux<RolePermissionRelation> findAll();

    Flux<RolePermissionRelation> addPermissionToRole(Collection<RolePermissionRelation> item);
}
