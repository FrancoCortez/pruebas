package cl.pim.auth.handler;

import cl.pim.auth.dto.rolepermissionrelation.AddRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.NewRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.RolePermissionRelationResourceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RolePermissionRelationHandler {
    Mono<RolePermissionRelationResourceDto> create(NewRolePermissionRelationResourceDto item);

    Flux<RolePermissionRelationResourceDto> findAll();


    Flux<RolePermissionRelationResourceDto> addPermissionToRole(AddRolePermissionRelationResourceDto item);
}
