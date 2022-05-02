package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.dto.role.UpdateRoleResourceDto;
import cl.pim.auth.handler.RoleHandler;
import cl.pim.auth.mapper.RoleMapper;
import cl.pim.auth.service.RolePermissionRelationService;
import cl.pim.auth.service.RoleService;
import cl.pim.auth.service.UserRoleRelationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.ResponseEntity.noContent;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleImplHandler implements RoleHandler {
    private final RoleService roleService;
    private final UserRoleRelationService userRoleRelationService;
    private final RolePermissionRelationService rolePermissionRelationService;
    private final RoleMapper roleMapper;

    @Override
    @Transactional
    public Mono<RoleResourceDto> create(NewRoleResourceDto item) {
        return this.roleService
                .create(this.roleMapper.toModel(item))
                .map(this.roleMapper::toResource);
    }


    @Override
    @Transactional
    public Mono<RoleResourceDto> update(String id, UpdateRoleResourceDto item) {
        return this.roleService.findById(id)
                .map(role -> this.roleMapper.toUpdate(item, role))
                .flatMap(this.roleService::update)
                .map(this.roleMapper::toResource);
    }


    @Transactional
    public Mono<ResponseEntity<Void>> deleteById(String id) {
        return this.roleService.deleteById(id)
                .then(this.userRoleRelationService.deleteByRoleId(id))
                .then(this.rolePermissionRelationService.deleteByRoleId(id))
                .map(empty -> noContent().build());
    }

    @Override
    @Transactional
    public Mono<ResponseEntity<Void>> deleteMassiveByIds(List<String> ids) {
        return this.roleService.deleteMassiveIds(ids)
                .then(this.userRoleRelationService.deleteMassiveRoleIds(ids))
                .then(this.rolePermissionRelationService.deleteMassiveRoleIds(ids))
                .map(empty -> noContent().build());
    }

    @Override
    public Mono<RoleResourceDto> findById(String id) {
        return this.roleService.findById(id)
                .map(this.roleMapper::toResource);
    }


    public Flux<RoleResourceDto> findAll() {
        return this.roleService
                .findAll()
                .map(this.roleMapper::toResource);
    }
}
