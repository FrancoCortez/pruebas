package clpim.pimcoreigdb.handler;

import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.dto.family.NewFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.PatchFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.UpdateFamilyResourceDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FamilyHandler {
    Mono<FamilyResourceDto> create(NewFamilyResourceDto item);

    Mono<FamilyResourceDto> update(String id, Long version, UpdateFamilyResourceDto item);

    Mono<FamilyResourceDto> patch(String id, Long version, PatchFamilyResourceDto item);

    Flux<FamilyResourceDto> findAll();

    Mono<FamilyResourceDto> findById(String id);

}
