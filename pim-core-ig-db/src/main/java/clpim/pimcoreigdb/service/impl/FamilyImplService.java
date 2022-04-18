package clpim.pimcoreigdb.service.impl;

import clpim.pimcoreigdb.model.FamilyEntity;
import clpim.pimcoreigdb.repository.FamilyGroupRepository;
import clpim.pimcoreigdb.repository.FamilyRepository;
import clpim.pimcoreigdb.service.FamilyService;
import clpim.pimcoreigdb.shared.enumes.BasicStatusEnum;
import clpim.pimcoreigdb.shared.exceptions.FamilyGroupNotFoundException;
import com.google.common.base.CaseFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FamilyImplService implements FamilyService {

    private final FamilyRepository familyRepository;
    private final FamilyGroupRepository familyGroupRepository;

    /**
     * @param item
     * @return
     */
    public Mono<FamilyEntity> create(FamilyEntity item) {
        item.setCode(CaseFormat
                .UPPER_UNDERSCORE
                .to(CaseFormat.LOWER_CAMEL, item.getName()
                        .toUpperCase()
                        .replaceAll(" ", "_")));
        item.setStatus(BasicStatusEnum.ENABLED);
        item.setId(UUID.randomUUID().toString());
        item.setIsNew(true);
        return this.familyRepository.save(item);
    }

    /**
     * @param item
     * @return
     */
    public Mono<FamilyEntity> update(FamilyEntity item) {
        if (item.getId() == null) {
            return Mono.error(new IllegalArgumentException("When updating an item, the id must be provided"));
        }
        item.setIsNew(false);
        assert item.getFamilyGroupEntity().getId() != null;
        return this.verifyExistence(item.getId())
                .then(this.familyGroupRepository.findById(item.getFamilyGroupEntity().getId()))
                .map(currentGroup -> {
                    item.setFamilyGroupEntity(currentGroup);
                    return item;
                })
                .then(this.familyRepository.save(item));
    }

    /**
     * @return
     */
    public Flux<FamilyEntity> findAll() {
        return this.familyRepository.findAll();
    }

    /**
     * @param id
     * @return
     */
    public Mono<FamilyEntity> findById(String id) {
        return this.familyRepository.findById(id);
    }

    /**
     * @param id
     * @return
     */
    private Mono<Boolean> verifyExistence(String id) {
        return this.familyRepository.existsById(id).handle((exists, sink) -> {
            if (Boolean.FALSE.equals(exists)) {
                sink.error(new FamilyGroupNotFoundException(id));
            } else {
                sink.next(exists);
            }
        });
    }
}
