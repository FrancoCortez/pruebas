package cl.pim.auth.handler.impl;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import cl.pim.auth.handler.AuthHandler;
import cl.pim.auth.mapper.UserMapper;
import cl.pim.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthImplHandler implements AuthHandler {

    private final AuthService authService;
    private final UserMapper userMapper;

    @Transactional
    public Mono<UserResourceDto> create(NewUserResourceDto item) {
        return this.authService
                .create(this.userMapper.toModel(item))
                .map(this.userMapper::toResource);
    }

    public Mono<?> login(LoginResourceDto item) {
        return this.authService
                .login(item)
                .map(this.userMapper::toLogin);
    }

}
