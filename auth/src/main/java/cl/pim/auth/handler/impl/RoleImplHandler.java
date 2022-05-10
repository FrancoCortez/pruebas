package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.role.MassiveDeletedRoleResourceDto;
import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.dto.role.UpdateRoleResourceDto;
import cl.pim.auth.handler.RoleHandler;
import cl.pim.auth.mapper.RoleMapper;
import cl.pim.auth.service.RolePermissionRelationService;
import cl.pim.auth.service.RoleService;
import cl.pim.auth.service.UserRoleRelationService;
import cl.pim.auth.shared.validation.ValidateObjectHandlerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RoleImplHandler implements RoleHandler {
    private final RoleService roleService;
    private final UserRoleRelationService userRoleRelationService;
    private final RolePermissionRelationService rolePermissionRelationService;
    private final RoleMapper roleMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

//    @Override
//    @Transactional
//    public Mono<RoleResourceDto> create(NewRoleResourceDto item) {
//        return this.roleService
//                .create(this.roleMapper.toModel(item))
//                .map(this.roleMapper::toResource);
//    }

    public @NotNull Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.roleService.create(this.roleMapper.toModel(body.toFuture().join())).map(this.roleMapper::toResource),
                                RoleResourceDto.class
                        )
                , request, NewRoleResourceDto.class);
    }

    @Transactional
    public @NotNull Mono<ServerResponse> update(final ServerRequest request) {
        String id = request.pathVariable("id");
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.roleService.findById(id)
                                        .map(role -> this.roleMapper.toUpdate(body.toFuture().join(), role))
                                        .flatMap(this.roleService::update)
                                        .map(this.roleMapper::toResource)
                                , RoleResourceDto.class
                        )
                , request, UpdateRoleResourceDto.class);
    }

    @Transactional
    public @NotNull Mono<ServerResponse> deleteById(final ServerRequest request) {
        String id = request.pathVariable("id");
        return this.roleService.deleteById(id)
                .then(this.userRoleRelationService.deleteByRoleId(id))
                .then(this.rolePermissionRelationService.deleteByRoleId(id))
                .flatMap(f -> ServerResponse.noContent().build());
    }

    @Transactional
    public @NotNull Mono<ServerResponse> deleteMassiveByIds(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        this.roleService.deleteMassiveIds(body.toFuture().join().getIds())
                                .then(this.userRoleRelationService.deleteMassiveRoleIds(body.toFuture().join().getIds()))
                                .then(this.rolePermissionRelationService.deleteMassiveRoleIds(body.toFuture().join().getIds()))
                                .flatMap(f -> ServerResponse.noContent().build())
                , request, MassiveDeletedRoleResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findById(final ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(
                this.roleService.findById(id)
                        .map(this.roleMapper::toResource)
                , RoleResourceDto.class
        );
    }

    public @NotNull Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(
                this.roleService.findAll()
                        .map(this.roleMapper::toResource)
                , RoleResourceDto.class
        );
    }
}
