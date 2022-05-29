package cl.pim.auth.config;

import cl.pim.auth.utils.AuthenticationManager;
import cl.pim.auth.utils.SecurityContextRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class WebSecurityConfig {
    private static final String[] AUTH_WHITELIST = {
            // -- Swagger UI v2
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            // -- Swagger UI v3 (OpenAPI)
            "/v3/api-docs/**",
            "/swagger-ui/**"
            // other public endpoints of your API may be appended to this array
    };
    private final AuthenticationManager authenticationManager;
    private final SecurityContextRepository securityContextRepository;

    public WebSecurityConfig(final AuthenticationManager authenticationManager, final SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.securityContextRepository = securityContextRepository;
    }

    @Bean
    public SecurityWebFilterChain securitygWebFilterChain(final ServerHttpSecurity http) {
        return http.csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .authenticationManager(authenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange()
                .pathMatchers(HttpMethod.OPTIONS).permitAll()
                .pathMatchers("/auth/**").permitAll()
                .pathMatchers("/register").permitAll()
                .pathMatchers("/auth/login").permitAll()
                .pathMatchers("/roles/**").permitAll()
                .pathMatchers("/permission/**").permitAll()
                .pathMatchers("/role-permission-relation/**").permitAll()
                .pathMatchers("/user-role-relations/**").permitAll()
                .pathMatchers(AUTH_WHITELIST).permitAll()
                .anyExchange().authenticated()
                .and().build();

    }
}
