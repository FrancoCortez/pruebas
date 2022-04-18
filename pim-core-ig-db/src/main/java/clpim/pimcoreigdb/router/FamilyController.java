package clpim.pimcoreigdb.router;

import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.dto.family.NewFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.PatchFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.UpdateFamilyResourceDto;
import clpim.pimcoreigdb.handler.FamilyHandler;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/families")
@RequiredArgsConstructor
public class FamilyController {

    private final FamilyHandler familyHandler;

    @PostMapping
    public Mono<FamilyResourceDto> create(@Valid @RequestBody final NewFamilyResourceDto item) {
        return this.familyHandler.create(item);
    }

    @PutMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public Mono<FamilyResourceDto> update(@PathVariable @NotNull final String id,
                                          @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
                                          @Valid @RequestBody final UpdateFamilyResourceDto item) {
        return this.familyHandler.update(id, version, item);
    }

    @PatchMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public Mono<FamilyResourceDto> patch(@PathVariable @NotNull final String id,
                                         @RequestHeader(value = HttpHeaders.IF_MATCH) final Long version,
                                         @Valid @RequestBody final PatchFamilyResourceDto item) {
        return this.familyHandler.patch(id, version, item);
    }


    @GetMapping
    public Flux<FamilyResourceDto> findAll() {
        return this.familyHandler.findAll();
    }

    @GetMapping(value = "/{id}", produces = {APPLICATION_JSON_VALUE})
    public Mono<FamilyResourceDto> findById(@PathVariable final String id) {
        return this.familyHandler.findById(id);
    }
}
