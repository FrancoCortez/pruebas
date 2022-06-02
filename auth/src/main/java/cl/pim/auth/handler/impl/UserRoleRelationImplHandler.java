package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.userrolerelation.NewUserRoleRelationResourceDto;
import cl.pim.auth.dto.userrolerelation.UserRoleRelationResourceDto;
import cl.pim.auth.handler.UserRoleRelationHandler;
import cl.pim.auth.mapper.UserRoleRelationMapper;
import cl.pim.auth.service.UserRoleRelationService;
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
public class UserRoleRelationImplHandler implements UserRoleRelationHandler {
    private final UserRoleRelationService userRoleRelationService;
    private final UserRoleRelationMapper userRoleRelationMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

    public @NotNull Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.userRoleRelationService
                                        .create(this.userRoleRelationMapper.toModel(body.toFuture().join()))
                                        .map(this.userRoleRelationMapper::toResource)
                                , UserRoleRelationResourceDto.class
                        )
                , request, NewUserRoleRelationResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(
                this.userRoleRelationService.findAll()
                        .map(this.userRoleRelationMapper::toResource)
                , UserRoleRelationResourceDto.class
        );
    }
}
