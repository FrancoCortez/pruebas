package cl.pim.auth.service.impl;

import cl.pim.auth.model.Role;
import cl.pim.auth.repository.RoleRepository;
import cl.pim.auth.service.RoleService;
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
public class RoleImplService implements RoleService {

    private final RoleRepository roleRepository;

    public Mono<Role> create(Role item) {
        item.setId(UUID.randomUUID().toString());
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setIsNew(true);
        item.setCode(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, item.getName().toUpperCase().replaceAll(" ", "_")));
        return this.roleRepository.save(item);
    }

    public Mono<Role> update(Role entity) {
        if (entity.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating an item, the id must be provided"));
        }
        entity.setIsNew(false);
        entity.setCode(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, entity.getName().toUpperCase().replaceAll(" ", "_")));
        return this.verifyExistence(entity.getId())
                .then(roleRepository.save(entity));
    }

    public Mono<Void> deleteById(String id) {
        return this.roleRepository.deleteById(id);
    }

    public Mono<Void> deleteMassiveIds(List<String> ids) {
        return this.roleRepository.deleteAllById(ids);
    }

    public Flux<Role> findAll() {
        return this.roleRepository.findAll();
    }

    public Mono<Role> findById(String id) {
        return this.roleRepository.findById(id);
    }

    private Mono<Boolean> verifyExistence(String id) {
        return this.roleRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new RoleNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }


}
