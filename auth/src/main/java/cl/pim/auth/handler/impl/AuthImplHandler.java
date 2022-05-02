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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthImplHandler implements AuthHandler {

    private final AuthService authService;
    private final UserRoleRelationService userRoleRelationService;
    private final UserMapper userMapper;
    private final UserRoleRelationMapper userRoleRelationMapper;

    @Transactional
    public Mono<UserResourceDto> create(NewUserResourceDto item) {
        return this.authService
                .create(this.userMapper.toModel(item))
                .flatMap(user ->
                        this.userRoleRelationService
                                .create(this.userRoleRelationMapper.toModel(user, item.getRoles()))
                                .collectList()
                                // .then(Mono.just(this.userMapper.toResource(user)))
                                .then(this.authService.findById(user.getId())
                                        .map(this.userMapper::toResource))
                );
    }

    public Mono<LoginResponseResourceDto> login(LoginResourceDto item) {
        return this.authService
                .login(item)
                .map(this.userMapper::toLogin);
    }

    public Mono<UserResourceDto> findById(String id) {
        return this.authService
                .findById(id)
                .map(this.userMapper::toResource);
    }

}
