package clpim.pimcoreigdb.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface AttributeGroupHandler {
    Mono<ServerResponse> create(ServerRequest request);

    Mono<ServerResponse> findAll(ServerRequest request);
}
