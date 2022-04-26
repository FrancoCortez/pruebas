package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.handler.RoleHandler;
import cl.pim.auth.mapper.RoleMapper;
import cl.pim.auth.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RoleImplHandler implements RoleHandler {
    private final RoleService roleService;
    private final RoleMapper roleMapper;

    @Override
    public Mono<RoleResourceDto> create(NewRoleResourceDto item) {
        return this.roleService
                .create(this.roleMapper.toModel(item))
                .map(this.roleMapper::toResource);
    }

    public Flux<RoleResourceDto> findAll() {
        return this.roleService
                .findAll()
                .map(this.roleMapper::toResource);
    }
}
