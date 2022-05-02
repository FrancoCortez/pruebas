package cl.pim.auth.service;

import reactor.core.publisher.Mono;

import java.util.List;

public interface RolePermissionRelationService {

    Mono<Void> deleteByRoleId(String id);

    Mono<Void> deleteMassiveRoleIds(List<String> id);
}
