package cl.pim.auth.router;

import cl.pim.auth.dto.userrolerelation.NewUserRoleRelationResourceDto;
import cl.pim.auth.dto.userrolerelation.UserRoleRelationResourceDto;
import cl.pim.auth.handler.UserRoleRelationHandler;
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
public class UserRoleRelationRoute {

    private static final String BASE = "/user-role-relations";
    private final UserRoleRelationHandler handler;

    @RouterOperations({
            @RouterOperation(path = BASE, produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = UserRoleRelationHandler.class,
                    beanMethod = "create",
                    operation = @Operation(operationId = "createUserRoleRelation", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserRoleRelationResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = NewUserRoleRelationResourceDto.class)))
                    )),
//            @RouterOperation(path = "/role-permission-relation/add/permission-to-roles", produces = {
//                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
//                    beanClass = RolePermissionRelationHandler.class,
//                    beanMethod = "addPermissionToRole",
//                    operation = @Operation(operationId = "addPermissionToRole", responses = {
//                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = RolePermissionRelationResourceDto.class))),
//                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
//                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = AddRolePermissionRelationResourceDto.class)))
//                    )),
            @RouterOperation(path = BASE, produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = UserRoleRelationHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(operationId = "findAllRolePermissionRelation", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserRoleRelationResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }
                    )),
    })
    @Bean(name = "user-role-relations")
    public RouterFunction<ServerResponse> router() {
        return nest(path(BASE),
                route(POST("").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::create)
                        .andRoute(GET(""), handler::findAll)

        );
    }

}
