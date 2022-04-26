package cl.pim.auth.service.impl;

import cl.pim.auth.model.UserRoleRelation;
import cl.pim.auth.repository.UserRoleRelationRepository;
import cl.pim.auth.service.UserRoleRelationService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserRoleRelationImplService implements UserRoleRelationService {
    private final UserRoleRelationRepository userRoleRelationRepository;

    public Flux<UserRoleRelation> create(Collection<UserRoleRelation> item) {
        return this.userRoleRelationRepository.saveAll(item.stream().peek(m -> {
            m.setId(UUID.randomUUID().toString());
            m.setStatus(BasicStatusEnum.ENABLED);
            m.setIsNew(true);
        }).collect(Collectors.toSet()));
    }
}
