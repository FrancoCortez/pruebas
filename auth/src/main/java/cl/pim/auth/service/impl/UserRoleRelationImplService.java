package cl.pim.auth.service.impl;

import cl.pim.auth.model.UserRoleRelation;
import cl.pim.auth.repository.UserRoleRelationRepository;
import cl.pim.auth.service.UserRoleRelationService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
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

    public Flux<UserRoleRelation> create(Collection<UserRoleRelation> item) {
        return this.userRoleRelationRepository.saveAll(item.stream().peek(m -> {
            m.setId(UUID.randomUUID().toString());
            m.setStatus(BasicStatusEnum.ENABLED);
            m.setIsNew(true);
        }).collect(Collectors.toSet()));
    }

    public Mono<Void> deleteByRoleId(String id) {
        log.info("llegue a eliminar el id: " + id);
        return this.userRoleRelationRepository.deleteByRoleId(id);
    }

    public Mono<Void> deleteMassiveRoleIds(List<String> idsRoles) {
        return this.userRoleRelationRepository.deleteAllByRoleId(idsRoles);
    }
}
