package clpim.pimcoreigdb.handler.impl;

import clpim.pimcoreigdb.dto.familygroup.FamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.NewFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.PatchFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.UpdateFamilyGroupResourceDto;
import clpim.pimcoreigdb.handler.FamilyGroupHandler;
import clpim.pimcoreigdb.mapper.FamilyGroupMapper;
import clpim.pimcoreigdb.service.FamilyGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
@Slf4j
public class FamilyGroupImplHandler implements FamilyGroupHandler {
    private final FamilyGroupService familyGroupService;
    private final FamilyGroupMapper familyGroupMapper;


    /**
     * @param newFamilyGroupResourceDto
     * @return
     */
    @Transactional
    public Mono<FamilyGroupResourceDto> create(NewFamilyGroupResourceDto newFamilyGroupResourceDto) {
        return this.familyGroupService
                .create(this.familyGroupMapper.toModel(newFamilyGroupResourceDto))
                .map(this.familyGroupMapper::toResource);
    }

    /**
     * @param id
     * @param version
     * @param updateFamilyGroupResourceDto
     * @return
     */
    @Transactional
    public Mono<FamilyGroupResourceDto> update(String id, Long version, UpdateFamilyGroupResourceDto updateFamilyGroupResourceDto) {
        return this.familyGroupService.findById(id)
                .map(familyGroupEntity -> familyGroupMapper.toUpdate(updateFamilyGroupResourceDto, familyGroupEntity))
                .flatMap(this.familyGroupService::update)
                .map(this.familyGroupMapper::toResource);
    }

    /**
     * @param id
     * @param version
     * @param patchFamilyGroupResourceDto
     * @return
     */
    @Transactional
    public Mono<FamilyGroupResourceDto> patch(String id, Long version, PatchFamilyGroupResourceDto patchFamilyGroupResourceDto) {
        return this.familyGroupService.findById(id)
                .map(familyGroupEntity -> familyGroupMapper.patch(patchFamilyGroupResourceDto, familyGroupEntity))
                .flatMap(this.familyGroupService::update)
                .map(this.familyGroupMapper::toResource);
    }

    /**
     * @return
     */
    public Flux<FamilyGroupResourceDto> findAll() {
        return this.familyGroupService.findAll()
                .map(this.familyGroupMapper::toResource);
    }

    /**
     * @param id
     * @return
     */
    public Mono<FamilyGroupResourceDto> findById(String id) {
        return this.familyGroupService.findById(id)
                .map(this.familyGroupMapper::toResource);
    }

}
