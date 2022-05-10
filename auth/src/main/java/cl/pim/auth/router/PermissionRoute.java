package cl.pim.auth.router;

import cl.pim.auth.dto.permission.MassiveDeletedPermissionResourceDto;
import cl.pim.auth.dto.permission.NewPermissionResourceDto;
import cl.pim.auth.dto.permission.PermissionResourceDto;
import cl.pim.auth.dto.permission.UpdatePermissionResourceDto;
import cl.pim.auth.handler.PermissionHandler;
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
public class PermissionRoute {

    private final PermissionHandler handler;

    @RouterOperations({
            @RouterOperation(path = "/permission", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = PermissionHandler.class,
                    beanMethod = "create",
                    operation = @Operation(operationId = "createPermission", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PermissionResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = NewPermissionResourceDto.class)))
                    )),
            @RouterOperation(path = "/permission/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.PUT,
                    beanClass = PermissionHandler.class,
                    beanMethod = "update",
                    operation = @Operation(operationId = "update-permission", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PermissionResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = UpdatePermissionResourceDto.class))),
                            parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = "/permission/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.DELETE,
                    beanClass = PermissionHandler.class,
                    beanMethod = "deleteById",
                    operation = @Operation(operationId = "deletePermissionById", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema())),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = "/permission/massive/deleted", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = PermissionHandler.class,
                    beanMethod = "deleteMassiveByIds",
                    operation = @Operation(operationId = "deleteMassivePermission", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema())),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = MassiveDeletedPermissionResourceDto.class)))
                    )),
            @RouterOperation(path = "/permission", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = PermissionHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(operationId = "findByIdPermission", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PermissionResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }
                    )),
            @RouterOperation(path = "/permission/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = PermissionHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(operationId = "findAllPermission", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PermissionResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = "/permission/find-by-role/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = PermissionHandler.class,
                    beanMethod = "findByRoleId",
                    operation = @Operation(operationId = "findPermissionByRoleId", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PermissionResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = "/permission/find-by-not-role/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = PermissionHandler.class,
                    beanMethod = "findPermissionNotByRoleId",
                    operation = @Operation(operationId = "findPermissionNotByRoleId", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = PermissionResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
    })
    @Bean(name = "permission")
    public RouterFunction<ServerResponse> router() {
        return nest(path("/permission"),
                route(POST("").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::create)
                        .andRoute(PUT("/{id}").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::update)
                        .andRoute(DELETE("/{id}"), handler::deleteById)
                        .andRoute(POST("massive/deleted"), handler::deleteMassiveByIds)
                        .andRoute(GET(""), handler::findAll)
                        .andRoute(GET("/{id}"), handler::findById)
                        .andRoute(GET("/find-by-role/{id}"), handler::findByRoleId)
                        .andRoute(GET("/find-by-not-role/{id}"), handler::findPermissionNotByRoleId)

        );
    }

}
