package cl.pim.auth.handler;

import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.dto.role.UpdateRoleResourceDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface RoleHandler {

    Mono<RoleResourceDto> create(NewRoleResourceDto item);

    Flux<RoleResourceDto> findAll();

    Mono<ResponseEntity<Void>> deleteById(String id);

    Mono<RoleResourceDto> update(String id, UpdateRoleResourceDto item);

    Mono<ResponseEntity<Void>> deleteMassiveByIds(List<String> ids);

    Mono<RoleResourceDto> findById(String id);
}
