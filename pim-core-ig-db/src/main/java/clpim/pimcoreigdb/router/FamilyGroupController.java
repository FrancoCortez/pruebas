package clpim.pimcoreigdb.router;

import clpim.pimcoreigdb.dto.familygroup.FamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.NewFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.PatchFamilyGroupResourceDto;
import clpim.pimcoreigdb.dto.familygroup.UpdateFamilyGroupResourceDto;
import clpim.pimcoreigdb.handler.FamilyGroupHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/family-group")
@RequiredArgsConstructor
@Slf4j
public class FamilyGroupController {

    private final FamilyGroupHandler familyGroupHandler;

    @PostMapping()
    public Mono<FamilyGroupResourceDto> create(@Valid @RequestBody final NewFamilyGroupResourceDto newFamilyGroupResourceDto) {
        return this.familyGroupHandler.create(newFamilyGroupResourceDto);
    }

    @PutMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public Mono<FamilyGroupResourceDto> update(@PathVariable @NotNull final String id,
                                               @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
                                               @Valid @RequestBody final UpdateFamilyGroupResourceDto updateFamilyGroupResourceDto) {
        return this.familyGroupHandler.update(id, version, updateFamilyGroupResourceDto);
    }

    @PatchMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public Mono<FamilyGroupResourceDto> patch(@PathVariable @NotNull final String id,
                                              @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
                                              @Valid @RequestBody final PatchFamilyGroupResourceDto patchFamilyGroupResourceDto) {
        return this.familyGroupHandler.patch(id, version, patchFamilyGroupResourceDto);
    }

    @GetMapping(produces = {APPLICATION_JSON_VALUE})
    public Flux<FamilyGroupResourceDto> findAll() {
        return this.familyGroupHandler.findAll();
    }

    @GetMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public Mono<FamilyGroupResourceDto> findById(@PathVariable final String id) {
        return this.familyGroupHandler.findById(id);
    }


}
