package cl.pim.auth.service;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.model.User;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<User> create(User user);

    Mono<User> login(LoginResourceDto item);
}
