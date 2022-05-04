package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.rolepermissionrelation.AddRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.NewRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.RolePermissionRelationResourceDto;
import cl.pim.auth.handler.RolePermissionRelationHandler;
import cl.pim.auth.mapper.RolePermissionRelationMapper;
import cl.pim.auth.service.RolePermissionRelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolePermissionRelationImplHandler implements RolePermissionRelationHandler {
    private final RolePermissionRelationService rolePermissionRelationService;
    private final RolePermissionRelationMapper rolePermissionRelationMapper;

    public Mono<RolePermissionRelationResourceDto> create(NewRolePermissionRelationResourceDto item) {
        return this.rolePermissionRelationService
                .create(this.rolePermissionRelationMapper.toModel(item))
                .map(this.rolePermissionRelationMapper::toResource);
    }


    @Override
    public Flux<RolePermissionRelationResourceDto> addPermissionToRole(AddRolePermissionRelationResourceDto item) {
        log.info(item.toString());
        return this.rolePermissionRelationService.addPermissionToRole(this.rolePermissionRelationMapper.addPermissionToRole(item))
                .map(this.rolePermissionRelationMapper::toResource);
    }

    @Override
    public Flux<RolePermissionRelationResourceDto> findAll() {
        return this.rolePermissionRelationService.findAll()
                .map(this.rolePermissionRelationMapper::toResource);
    }

}
