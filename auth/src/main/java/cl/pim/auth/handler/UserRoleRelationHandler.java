package cl.pim.auth.handler;

import org.jetbrains.annotations.NotNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface UserRoleRelationHandler {
    @NotNull Mono<ServerResponse> create(final ServerRequest request);
}
