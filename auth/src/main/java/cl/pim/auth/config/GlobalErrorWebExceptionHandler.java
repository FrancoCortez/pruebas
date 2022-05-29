package cl.pim.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

@Configuration
@Order(-2)
@Slf4j
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {
    public GlobalErrorWebExceptionHandler(GlobalErrorAttributes g, ApplicationContext applicationContext,
                                          ServerCodecConfigurer serverCodecConfigurer) {
        super(g, new WebProperties.Resources(), applicationContext);
        super.setMessageWriters(serverCodecConfigurer.getWriters());
        super.setMessageReaders(serverCodecConfigurer.getReaders());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(final ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private @NotNull Mono<ServerResponse> renderErrorResponse(final ServerRequest request) {
        final Map<String, Object> errorPropertiesMap = getErrorAttributes(request, ErrorAttributeOptions.of(ErrorAttributeOptions.Include.STACK_TRACE));
        return ServerResponse.status(Integer.parseInt(errorPropertiesMap.get("status").toString()))
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorPropertiesMap));
    }
}
