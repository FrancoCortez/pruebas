package cl.pim.auth.service;

import cl.pim.auth.model.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleService {

    Mono<Role> create(Role item);

    Flux<Role> findAll();
}
