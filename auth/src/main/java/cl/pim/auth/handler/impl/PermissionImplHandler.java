package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.permission.NewPermissionResourceDto;
import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.permission.UpdatePermissionResourceDto;
import cl.pim.auth.handler.PermissionHandler;
import cl.pim.auth.mapper.PermissionMapper;
import cl.pim.auth.service.PermissionService;
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
public class PermissionImplHandler implements PermissionHandler {
    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;

    @Override
    public Mono<PermissionResourceDto> create(NewPermissionResourceDto item) {
        return this.permissionService
                .create(this.permissionMapper.toModel(item))
                .map(this.permissionMapper::toResource);
    }

    @Override
    public Mono<PermissionResourceDto> update(String id, UpdatePermissionResourceDto item) {
        return this.permissionService.findById(id)
                .map(permission -> this.permissionMapper.toUpdate(item, permission))
                .flatMap(this.permissionService::update)
                .map(this.permissionMapper::toResource);
    }

    @Override
    @Transactional
    public Mono<ResponseEntity<Void>> deleteById(String id) {
        return this.permissionService.deleteById(id)
                .map(empty -> noContent().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteMassiveByIds(List<String> ids) {
        return this.permissionService.deleteMassiveIds(ids)
                .map(empty -> noContent().build());
    }

    @Override
    public Flux<PermissionResourceDto> findAll() {
        return this.permissionService
                .findAll()
                .map(this.permissionMapper::toResource);
    }

    @Override
    public Mono<PermissionResourceDto> findById(String id) {
        return this.permissionService.findById(id)
                .map(this.permissionMapper::toResource);
    }

    public Flux<PermissionResourceDto> findByRoleId(String roleId) {
        return this.permissionService.findByRoleId(roleId)
                .map(this.permissionMapper::toResource);
    }

    public Flux<PermissionResourceDto> findPermissionNotByRoleId(String roleId) {
        return this.permissionService.findPermissionNotByRoleId(roleId)
                .map(this.permissionMapper::toResource);
    }
}
