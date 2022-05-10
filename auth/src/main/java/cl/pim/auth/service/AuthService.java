package cl.pim.auth.service;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.model.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<User> create(User user);

    Mono<String> login(LoginResourceDto item);

    Mono<User> findById(String userId);

    Flux<User> findAll();
}
