package clpim.pimcoreigdb.handler;

import clpim.pimcoreigdb.dto.familygroup.FamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.NewFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.PatchFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.UpdateFamilyGroupResourceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FamilyGroupHandler {

    Flux<FamilyGroupResourceDto> findAll();

    Mono<FamilyGroupResourceDto> create(NewFamilyGroupResourceDto newFamilyGroupResourceDto);

    Mono<FamilyGroupResourceDto> findById(String id);

    Mono<FamilyGroupResourceDto> update(String id, Long version, UpdateFamilyGroupResourceDto updateFamilyGroupResourceDto);

    Mono<FamilyGroupResourceDto> patch(String id, Long version, PatchFamilyGroupResourceDto patchFamilyGroupResourceDto);

}
