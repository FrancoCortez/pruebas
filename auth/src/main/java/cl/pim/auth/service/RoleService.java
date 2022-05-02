package cl.pim.auth.service;

import cl.pim.auth.model.Role;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleService {

    Mono<Role> create(Role item);

    Mono<Role> update(Role entity);

    Mono<Void> deleteById(String id);

    Mono<Void> deleteMassiveIds(List<String> ids);

    Flux<Role> findAll();

    Mono<Role> findById(String id);
}
