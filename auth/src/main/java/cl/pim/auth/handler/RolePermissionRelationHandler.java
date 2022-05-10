package cl.pim.auth.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface RolePermissionRelationHandler {
    @NotNull Mono<ServerResponse> create(final ServerRequest request);

    @NotNull Mono<ServerResponse> addPermissionToRole(final ServerRequest request);

    @NotNull Mono<ServerResponse> findAll(final ServerRequest request);
}
