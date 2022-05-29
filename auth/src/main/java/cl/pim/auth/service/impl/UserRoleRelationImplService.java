package cl.pim.auth.service.impl;

import cl.pim.auth.model.UserRoleRelation;
import cl.pim.auth.repository.RoleRepository;
import cl.pim.auth.repository.UserRepository;
import cl.pim.auth.repository.UserRoleRelationRepository;
import cl.pim.auth.service.UserRoleRelationService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import cl.pim.auth.shared.exceptions.RoleNotFoundException;
import cl.pim.auth.shared.exceptions.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleRelationImplService implements UserRoleRelationService {
    private final UserRoleRelationRepository userRoleRelationRepository;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public Flux<UserRoleRelation> createMassive(Collection<UserRoleRelation> item) {
        return this.userRoleRelationRepository.saveAll(item.stream().peek(m -> {
            m.setId(UUID.randomUUID().toString());
            m.setStatus(BasicStatusEnum.ENABLED);
            m.setIsNew(true);
        }).collect(Collectors.toSet()));
    }

    public Mono<UserRoleRelation> create(UserRoleRelation item) {
        item.setId(UUID.randomUUID().toString());
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setIsNew(true);
        return this.verifyExistenceRole(item.getRoleId())
                .then(this.verifyExistenceUser(item.getUserId()))
                .then(this.userRoleRelationRepository.save(item));
    }

    public Mono<Void> deleteByRoleId(String id) {
        log.info("llegue a eliminar el id: " + id);
        return this.userRoleRelationRepository.deleteByRoleId(id);
    }

    public Mono<Void> deleteMassiveRoleIds(List<String> idsRoles) {
        return this.userRoleRelationRepository.deleteAllByRoleId(idsRoles);
    }

    private Mono<Boolean> verifyExistenceRole(String id) {
        return this.roleRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new RoleNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }

    private Mono<Boolean> verifyExistenceUser(String id) {
        return this.userRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new UserNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
