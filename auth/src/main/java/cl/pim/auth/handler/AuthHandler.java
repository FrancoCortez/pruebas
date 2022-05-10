package cl.pim.auth.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface AuthHandler {

    @NotNull Mono<ServerResponse> create(final ServerRequest request);

    @NotNull Mono<ServerResponse> login(final ServerRequest request);

    @NotNull Mono<ServerResponse> findById(final ServerRequest request);

    @NotNull Mono<ServerResponse> findAll(ServerRequest serverRequest);

}
