package cl.pim.auth.handler;

import cl.pim.auth.dto.permission.NewPermissionResourceDto;
import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.permission.UpdatePermissionResourceDto;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface PermissionHandler {
    Mono<PermissionResourceDto> create(NewPermissionResourceDto item);

    Mono<PermissionResourceDto> update(String id, UpdatePermissionResourceDto item);

    Mono<ResponseEntity<Void>> deleteById(String id);

    Mono<ResponseEntity<Void>> deleteMassiveByIds(List<String> ids);

    Flux<PermissionResourceDto> findAll();

    Mono<PermissionResourceDto> findById(String id);

    Flux<PermissionResourceDto> findByRoleId(String roleId);

    Flux<PermissionResourceDto> findPermissionNotByRoleId(String roleId);
}
