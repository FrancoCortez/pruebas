package cl.pim.auth.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface RoleHandler {

    @NotNull Mono<ServerResponse> create(final ServerRequest request);

    @NotNull Mono<ServerResponse> update(final ServerRequest request);

    @NotNull Mono<ServerResponse> deleteById(final ServerRequest request);

    @NotNull Mono<ServerResponse> deleteMassiveByIds(final ServerRequest request);

    @NotNull Mono<ServerResponse> findAll(final ServerRequest request);

    @NotNull Mono<ServerResponse> findById(final ServerRequest request);
}
