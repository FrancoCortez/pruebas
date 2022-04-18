package clpim.pimcoreigdb.service.impl;

import clpim.pimcoreigdb.model.FamilyGroupEntity;
import clpim.pimcoreigdb.repository.FamilyGroupRepository;
import clpim.pimcoreigdb.repository.FamilyRepository;
import clpim.pimcoreigdb.service.FamilyGroupService;
import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import clpim.pimcoreigdb.shared.exceptions.FamilyGroupNotFoundException;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class FamilyGroupImplService implements FamilyGroupService {

    private final FamilyGroupRepository familyGroupRepository;
    private final FamilyRepository familyRepository;

    /**
     * @param familyGroupEntity
     * @return
     */
    public Mono<FamilyGroupEntity> create(FamilyGroupEntity familyGroupEntity) {
        familyGroupEntity.setCode(CaseFormat
                .UPPER_UNDERSCORE
                .to(CaseFormat.LOWER_CAMEL, familyGroupEntity.getName()
                        .toUpperCase()
                        .replaceAll(" ", "_")));
        familyGroupEntity.setStatus(BasicStatusEnum.ENABLED);
        familyGroupEntity.setId(UUID.randomUUID().toString());
        familyGroupEntity.setIsNew(true);
        return this.familyGroupRepository.save(familyGroupEntity);
    }

    /**
     * @param familyGroupEntity
     * @return
     */
    public Mono<FamilyGroupEntity> update(FamilyGroupEntity familyGroupEntity) {
        if (familyGroupEntity.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating an item, the id must be provided"));
        }
        familyGroupEntity.setIsNew(false);
        return this.verifyExistence(familyGroupEntity.getId())
                .then(this.familyGroupRepository.save(familyGroupEntity));
    }

    /**
     * @return
     */
    public Flux<FamilyGroupEntity> findAll() {
        return this.familyGroupRepository.findAll()
                .flatMap(f -> Mono.just(f)
                        .zipWith(this.familyRepository.findFamiliesByFamilyGroupId(f.getId()).collectList())
                        .map(result -> result.getT1().setFamilyEntities(result.getT2())));
    }

    /**
     * @param id
     * @return
     */
    public Mono<FamilyGroupEntity> findById(String id) {
        return this.familyGroupRepository.findById(id)
                .flatMap(f -> Mono.just(f)
                        .zipWith(this.familyRepository.findFamiliesByFamilyGroupId(f.getId()).collectList())
                        .map(result -> result.getT1().setFamilyEntities(result.getT2()))
                );
    }

    /**
     * @param id
     * @return
     */
    private Mono<Boolean> verifyExistence(String id) {
        return this.familyGroupRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new FamilyGroupNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
