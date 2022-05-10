package cl.pim.auth.router;

import cl.pim.auth.dto.role.MassiveDeletedRoleResourceDto;
import cl.pim.auth.dto.role.NewRoleResourceDto;
import cl.pim.auth.dto.role.RoleResourceDto;
import cl.pim.auth.dto.role.UpdateRoleResourceDto;
import cl.pim.auth.handler.RoleHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class RoleRoute {

    private final RoleHandler handler;

    @RouterOperations({
            @RouterOperation(path = "/roles", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = RoleHandler.class,
                    beanMethod = "create",
                    operation = @Operation(operationId = "createRole", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RoleResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = NewRoleResourceDto.class)))
                    )),
            @RouterOperation(path = "/roles/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT,
                    beanClass = RoleHandler.class,
                    beanMethod = "update",
                    operation = @Operation(operationId = "updateRole", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RoleResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UpdateRoleResourceDto.class))),
                            parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = "/roles/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE,
                    beanClass = RoleHandler.class,
                    beanMethod = "deleteById",
                    operation = @Operation(operationId = "deleteByIdRole", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema())),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = "/roles/massive/deleted", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = RoleHandler.class,
                    beanMethod = "deleteMassiveByIds",
                    operation = @Operation(operationId = "deleteMassiveByIdsRole", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema())),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = MassiveDeletedRoleResourceDto.class)))
                    )),
            @RouterOperation(path = "/roles", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = RoleHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(operationId = "findAllRole", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RoleResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }
                    )),
            @RouterOperation(path = "/roles/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = RoleHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(operationId = "findByIdRole", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RoleResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
    })
    @Bean(name = "roles")
    public RouterFunction<ServerResponse> router() {
        return nest(path("/roles"),
                route(POST("").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::create)
                        .andRoute(PUT("/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::update)
                        .andRoute(DELETE("/{id}"), handler::deleteById)
                        .andRoute(POST("/massive/deleted").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::deleteMassiveByIds)
                        .andRoute(GET(""), handler::findAll)
                        .andRoute(GET("/{id}"), handler::findById)


        );
    }

}
