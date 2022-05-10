package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.permission.MassiveDeletedPermissionResourceDto;
import cl.pim.auth.dto.permission.NewPermissionResourceDto;
import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.permission.UpdatePermissionResourceDto;
import cl.pim.auth.handler.PermissionHandler;
import cl.pim.auth.mapper.PermissionMapper;
import cl.pim.auth.service.PermissionService;
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
public class PermissionImplHandler implements PermissionHandler {
    private final PermissionService permissionService;
    private final PermissionMapper permissionMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

    @Transactional
    public @NotNull Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        this.permissionService
                                .create(this.permissionMapper.toModel(body.toFuture().join()))
                                .flatMap(f -> ServerResponse.ok().body(Mono.just(this.permissionMapper.toResource(f)), PermissionResourceDto.class))
                , request, NewPermissionResourceDto.class
        );
    }

    @Transactional
    public @NotNull Mono<ServerResponse> update(final ServerRequest request) {
        String id = request.pathVariable("id");
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        this.permissionService.findById(id)
                                .map(permission -> this.permissionMapper.toUpdate(body.toFuture().join(), permission))
                                .flatMap(this.permissionService::update)
                                .flatMap(f -> ServerResponse.ok().body(Mono.just(this.permissionMapper.toResource(f)), PermissionResourceDto.class))
                , request, UpdatePermissionResourceDto.class);
    }

    @Transactional
    public @NotNull Mono<ServerResponse> deleteById(final ServerRequest request) {
        String id = request.pathVariable("id");
        return this.permissionService.deleteById(id)
                .flatMap(f -> ServerResponse.noContent().build());
    }

    @Transactional
    public @NotNull Mono<ServerResponse> deleteMassiveByIds(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        this.permissionService.deleteMassiveIds(body.toFuture().join().getIds())
                                .flatMap(f -> ServerResponse.noContent().build())
                , request, MassiveDeletedPermissionResourceDto.class
        );
    }

    public @NotNull Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(this.permissionService.findAll()
                .map(this.permissionMapper::toResource), PermissionResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findById(final ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(this.permissionService.findById(id)
                .map(this.permissionMapper::toResource), PermissionResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findByRoleId(final ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(this.permissionService.findByRoleId(id)
                .map(this.permissionMapper::toResource), PermissionResourceDto.class);
    }

    //    public Flux<PermissionResourceDto> findPermissionNotByRoleId(String roleId) {
//        return this.permissionService.findPermissionNotByRoleId(roleId)
//                .map(this.permissionMapper::toResource);
//    }
    public @NotNull Mono<ServerResponse> findPermissionNotByRoleId(final ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(this.permissionService.findPermissionNotByRoleId(id)
                .map(this.permissionMapper::toResource), PermissionResourceDto.class);
    }
}
