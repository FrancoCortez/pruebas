package clpim.pimcoreigdb.router;

import clpim.pimcoreigdb.dto.attributegroup.AttributeGroupResourceDto;
import clpim.pimcoreigdb.dto.attributegroup.NewAttributeGroupResourceDto;
import clpim.pimcoreigdb.handler.AttributeGroupHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.RouterOperation;
import org.springdoc.core.annotations.RouterOperations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class AttributeGroupRoute {

    private final AttributeGroupHandler handler;

    @Bean(name = "attribute-group")
    @RouterOperations({
            @RouterOperation(path = "/attribute-group", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = AttributeGroupHandler.class,
                    beanMethod = "create",
                    operation = @Operation(operationId = "create-attribute-group", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = AttributeGroupResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = NewAttributeGroupResourceDto.class)))
                    )),
            @RouterOperation(path = "/attribute-group", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = AttributeGroupHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(operationId = "find-all-attribute-group", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = AttributeGroupResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }// parameters = {@Parameter(in = ParameterIn.PATH, name = "employeeId")}
                    ))
    })
    public RouterFunction<ServerResponse> router() {
        return nest(path("attribute-group"),
                route(POST("").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::create)
                        .andRoute(GET(""), handler::findAll)
        );
    }
}
