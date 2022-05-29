package cl.pim.auth.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Swagger Demo", version = "1.0", description = "Documentation APIs v1.0"))
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApiAuth() {
        return GroupedOpenApi.builder()
                .group("auth")
                .pathsToMatch("/auth/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApiPermission() {
        return GroupedOpenApi.builder()
                .group("permission")
                .pathsToMatch("/permission/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApiRolePermissionRelation() {
        return GroupedOpenApi.builder()
                .group("role-permission-relation")
                .pathsToMatch("/role-permission-relation/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApiRole() {
        return GroupedOpenApi.builder()
                .group("role")
                .pathsToMatch("/roles/**")
                .build();
    }

    @Bean
    public GroupedOpenApi publicApiUserRoleRelation() {
        return GroupedOpenApi.builder()
                .group("user-role-relations")
                .pathsToMatch("/user-role-relations/**")
                .build();
    }
}
