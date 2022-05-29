package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.dto.auth.LoginResponseResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import cl.pim.auth.handler.AuthHandler;
import cl.pim.auth.mapper.UserMapper;
import cl.pim.auth.mapper.UserRoleRelationMapper;
import cl.pim.auth.service.AuthService;
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
public class AuthImplHandler implements AuthHandler {

    private final AuthService authService;
    private final UserRoleRelationService userRoleRelationService;
    private final UserMapper userMapper;
    private final UserRoleRelationMapper userRoleRelationMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

    @Transactional
    public @NotNull Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        this.authService
                                .create(this.userMapper.toModel(body.toFuture().join()))
                                .flatMap(user ->
                                        this.userRoleRelationService
                                                .createMassive(this.userRoleRelationMapper.toModelList(user, body.toFuture().join().getRoles()))
                                                .collectList()
                                                .then(this.authService.findById(user.getId()))
                                                .flatMap(f -> ServerResponse.ok().body(Mono.just(this.userMapper.toResource(f)), UserResourceDto.class))
                                )
                , request, NewUserResourceDto.class);
    }


    public @NotNull Mono<ServerResponse> login(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                this.authService
                        .login(body.toFuture().join())
                        .flatMap(f -> ServerResponse.ok().body(Mono.just(this.userMapper.toLogin(f)), LoginResponseResourceDto.class)
                        ), request, LoginResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findById(final ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(this.authService.findById(id).map(this.userMapper::toResource), UserResourceDto.class);
    }

    @Override
    public @NotNull Mono<ServerResponse> findAll(final ServerRequest serverRequest) {
        return ServerResponse.ok().body(this.authService.findAll().map(this.userMapper::toResource), UserResourceDto.class);
    }

}
