package clpim.pimcoreigdb.handler.impl;

import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.dto.family.NewFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.PatchFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.UpdateFamilyResourceDto;
import clpim.pimcoreigdb.handler.FamilyHandler;
import clpim.pimcoreigdb.mapper.FamilyMapper;
import clpim.pimcoreigdb.service.FamilyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FamilyImplHandler implements FamilyHandler {
    private final FamilyService familyService;

    private final FamilyMapper familyMapper;

    /**
     * @param item
     * @return
     */
    @Transactional
    public Mono<FamilyResourceDto> create(NewFamilyResourceDto item) {
        return this.familyService
                .create(this.familyMapper.toModel(item))
                .map(this.familyMapper::toResource);
    }

    /**
     * @param id
     * @param version
     * @param item
     * @return
     */
    @Transactional
    public Mono<FamilyResourceDto> update(String id, Long version, UpdateFamilyResourceDto item) {
        return this.familyService.findById(id)
                .map(family -> this.familyMapper.toUpdate(item, family))
                .flatMap(this.familyService::update)
                .map(this.familyMapper::toResource);
    }

    /**
     * @param id
     * @param version
     * @param item
     * @return
     */
    @Transactional
    public Mono<FamilyResourceDto> patch(String id, Long version, PatchFamilyResourceDto item) {
        return this.familyService.findById(id)
                .map(family -> this.familyMapper.patch(item, family))
                .flatMap(this.familyService::update)
                .map(this.familyMapper::toResource);
    }

    /**
     * @return
     */
    public Flux<FamilyResourceDto> findAll() {
        return this.familyService
                .findAll()
                .map(this.familyMapper::toResource);
    }

    /**
     * @param id
     * @return
     */
    public Mono<FamilyResourceDto> findById(String id) {
        return this.familyService
                .findById(id)
                .map(this.familyMapper::toResource);
    }

}
