package clpim.pimcoreigdb.handler.impl;

import clpim.pimcoreigdb.dto.attributegroup.AttributeGroupResourceDto;
import clpim.pimcoreigdb.dto.attributegroup.NewAttributeGroupResourceDto;
import clpim.pimcoreigdb.handler.AttributeGroupHandler;
import clpim.pimcoreigdb.mapper.AttributeGroupMapper;
import clpim.pimcoreigdb.service.AttributeGroupService;
import clpim.pimcoreigdb.shared.validation.ValidateObjectHandlerConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Slf4j
@Component
public class AttributeGroupImplHandler implements AttributeGroupHandler {
    private final AttributeGroupService attributeGroupService;
    private final AttributeGroupMapper attributeGroupMapper;
    private final ValidateObjectHandlerConfig validateObjectHandlerConfig;


    public Mono<ServerResponse> create(final ServerRequest request) {
        return this.validateObjectHandlerConfig
                .requireValidBody(
                        body -> this.attributeGroupService
                                .create(this.attributeGroupMapper.toModel(body.toFuture().join()))
                                .flatMap(f -> ServerResponse.ok().body(Mono.just(this.attributeGroupMapper.toResource(f)), AttributeGroupResourceDto.class)),
                        request,
                        NewAttributeGroupResourceDto.class);
    }

    public Mono<ServerResponse> findAll(final ServerRequest request) {
        return ServerResponse.ok().body(this.attributeGroupService.findAll().map(this.attributeGroupMapper::toResource), AttributeGroupResourceDto.class);
    }

}
