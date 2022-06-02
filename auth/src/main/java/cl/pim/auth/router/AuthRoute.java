package cl.pim.auth.router;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.dto.auth.LoginResponseResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import cl.pim.auth.handler.AuthHandler;
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
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RequestPredicates.contentType;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
public class AuthRoute {

    private final AuthHandler handler;
    private static final String BASE = "/auth";

    @RouterOperations({
            @RouterOperation(path = BASE + "/login", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = AuthHandler.class,
                    beanMethod = "login",
                    operation = @Operation(operationId = "login", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = LoginResponseResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = LoginResourceDto.class)))
                    )),
            @RouterOperation(path = BASE, produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.POST,
                    beanClass = AuthHandler.class,
                    beanMethod = "create",
                    operation = @Operation(operationId = "create-user", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied")
                    }, requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = NewUserResourceDto.class)))
                    )),
            @RouterOperation(path = BASE + "/find/user/{id}", produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = AuthHandler.class,
                    beanMethod = "findById",
                    operation = @Operation(operationId = "find-user-by-id", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }, parameters = {@Parameter(in = ParameterIn.PATH, name = "id")}
                    )),
            @RouterOperation(path = BASE, produces = {
                    MediaType.APPLICATION_JSON_VALUE}, method = RequestMethod.GET,
                    beanClass = AuthHandler.class,
                    beanMethod = "findAll",
                    operation = @Operation(operationId = "find-all-user", responses = {
                            @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = UserResourceDto.class))),
                            @ApiResponse(responseCode = "400", description = "Invalid Employee details supplied"),
                    }
                    )),
    })
    @Bean(name = BASE)
    public RouterFunction<ServerResponse> router() {
        return nest(path(BASE),
                route(POST("/login").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::login)
                        .andRoute(POST("").and(accept(APPLICATION_JSON)).and(contentType(APPLICATION_JSON)), handler::create)
                        .andRoute(GET("/find/user/{id}"), handler::findById)
                        .andRoute(GET(""), handler::findAll)
        );
    }

}
