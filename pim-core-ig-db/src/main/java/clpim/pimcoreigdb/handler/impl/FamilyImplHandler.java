package clpim.pimcoreigdb.handler.impl;

import clpim.pimcoreigdb.dto.family.FamilyResourceDto;
import clpim.pimcoreigdb.dto.family.NewFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.PatchFamilyResourceDto;
import clpim.pimcoreigdb.dto.family.UpdateFamilyResourceDto;
import clpim.pimcoreigdb.handler.FamilyHandler;
import clpim.pimcoreigdb.mapper.FamilyMapper;
import clpim.pimcoreigdb.service.FamilyService;
import clpim.pimcoreigdb.shared.validation.ValidateObjectHandlerConfig;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@RequiredArgsConstructor
public class FamilyImplHandler implements FamilyHandler {
    private final FamilyService familyService;
    private final FamilyMapper familyMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;

    @Transactional
    public @NotNull Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.familyService.create(this.familyMapper.toModel(body.toFuture().join()))
                                        .map(this.familyMapper::toResource)
                                , FamilyResourceDto.class
                        )
                , request, NewFamilyResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> update(final ServerRequest request) {
        String id = request.pathVariable("id");
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.familyService.findById(id)
                                        .map(family -> this.familyMapper.toUpdate(body.toFuture().join(), family))
                                        .flatMap(this.familyService::update)
                                        .map(this.familyMapper::toResource)
                                , FamilyResourceDto.class
                        )
                , request, UpdateFamilyResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> patch(final ServerRequest request) {
        String id = request.pathVariable("id");
        return this.validateObjectHandlerConfig.requireValidBody(body ->
                        ServerResponse.ok().body(
                                this.familyService.findById(id)
                                        .map(family -> this.familyMapper.patch(body.toFuture().join(), family))
                                        .flatMap(this.familyService::update)
                                        .map(this.familyMapper::toResource)
                                , FamilyResourceDto.class
                        )
                , request, PatchFamilyResourceDto.class);
    }

    public @NotNull Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(
                this.familyService.findAll()
                        .map(this.familyMapper::toResource)
                , FamilyResourceDto.class
        );
    }

    //    /**
//     * @param id
//     * @return
//     */
//    public Mono<FamilyResourceDto> findById(String id) {
//        return this.familyService
//                .findById(id)
//                .map(this.familyMapper::toResource);
//    }
    public @NotNull Mono<ServerResponse> findById(final ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok().body(
                this.familyService.findById(id)
                        .map(this.familyMapper::toResource)
                , FamilyResourceDto.class
        );
    }

}
