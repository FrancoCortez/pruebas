package cl.pim.auth.handler;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface RoleHandler {

    Mono<RoleResourceDto> create(NewRoleResourceDto item);

    Flux<RoleResourceDto> findAll();
}
