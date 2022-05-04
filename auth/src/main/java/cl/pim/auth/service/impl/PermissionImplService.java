package cl.pim.auth.service.impl;

import cl.pim.auth.model.Permission;
import cl.pim.auth.repository.PermissionRepository;
import cl.pim.auth.service.PermissionService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import cl.pim.auth.shared.exceptions.RoleNotFoundException;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PermissionImplService implements PermissionService {
    private final PermissionRepository permissionRepository;

    @Override
    public Mono<Permission> create(Permission item) {
        item.setId(UUID.randomUUID().toString());
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setIsNew(true);
        item.setCode(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, item.getName().toUpperCase().replaceAll(" ", "_")));
        log.info("llegue");
        return this.permissionRepository.save(item);
    }

    public Mono<Permission> update(Permission entity) {
        if (entity.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating an item, the id must be provided"));
        }
        entity.setIsNew(false);
        entity.setCode(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entity.getName().toUpperCase().replaceAll(" ", "_")));
        return this.verifyExistence(entity.getId())
                .then(permissionRepository.save(entity));
    }

    public Mono<Void> deleteById(String id) {
        return this.permissionRepository.deleteById(id);
    }

    public Mono<Void> deleteMassiveIds(List<String> ids) {
        return this.permissionRepository.deleteAllById(ids);
    }

    @Override
    public Flux<Permission> findAll() {
        return this.permissionRepository.findAll();
    }

    public Mono<Permission> findById(String id) {
        return this.permissionRepository.findById(id);
    }

    public Flux<Permission> findByRoleId(String roleId) {
        return this.permissionRepository.findPermissionByRoleId(roleId);
    }

    public Flux<Permission> findPermissionNotByRoleId(String roleId) {
        return this.permissionRepository.findPermissionNotByRoleId(roleId);
    }

    private Mono<Boolean> verifyExistence(String id) {
        return this.permissionRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new RoleNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
