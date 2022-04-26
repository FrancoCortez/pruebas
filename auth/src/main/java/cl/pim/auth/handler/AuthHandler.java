package cl.pim.auth.handler;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import reactor.core.publisher.Mono;

public interface AuthHandler {

    Mono<UserResourceDto> create(NewUserResourceDto item);
    Mono<?> login(LoginResourceDto item);

    Mono<UserResourceDto> findById(String id);
}
