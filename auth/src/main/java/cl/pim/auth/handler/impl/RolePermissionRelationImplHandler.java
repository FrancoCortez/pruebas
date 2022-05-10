package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.rolepermissionrelation.AddRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.NewRolePermissionRelationResourceDto;
import cl.pim.auth.dto.rolepermissionrelation.RolePermissionRelationResourceDto;
import cl.pim.auth.handler.RolePermissionRelationHandler;
import cl.pim.auth.mapper.RolePermissionRelationMapper;
import cl.pim.auth.service.RolePermissionRelationService;
import cl.pim.auth.shared.validation.ValidateObjectHandlerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class RolePermissionRelationImplHandler implements RolePermissionRelationHandler {
    private final RolePermissionRelationService rolePermissionRelationService;
    private final RolePermissionRelationMapper rolePermissionRelationMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

    public @NotNull Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.rolePermissionRelationService
                                        .create(this.rolePermissionRelationMapper.toModel(body.toFuture().join()))
                                        .map(this.rolePermissionRelationMapper::toResource)
                                , RolePermissionRelationResourceDto.class
                        )
                , request, NewRolePermissionRelationResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> addPermissionToRole(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.rolePermissionRelationService.addPermissionToRole(this.rolePermissionRelationMapper.addPermissionToRole(body.toFuture().join()))
                                        .map(this.rolePermissionRelationMapper::toResource)
                                , RolePermissionRelationResourceDto.class
                        )
                , request, AddRolePermissionRelationResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(
                this.rolePermissionRelationService.findAll()
                        .map(this.rolePermissionRelationMapper::toResource)
                , RolePermissionRelationResourceDto.class
        );
    }
}
