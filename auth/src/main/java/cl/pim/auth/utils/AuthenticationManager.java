package cl.pim.auth.utils;

import io.jsonwebtoken.Claims;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JWTUtils jwtUtils;

    public AuthenticationManager(final JWTUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    public Mono<Authentication> authenticate(final Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        String username;
        try {
            username = jwtUtils.getUsernameFromToken(authToken);
        } catch (Exception e) {
            username = null;
        }
        if (username != null && jwtUtils.validateToken(authToken)) {
            Claims claims = jwtUtils.getAllClaimsFromToken(authToken);
            List<String> rolesMap = claims.get("role", List.class);
            // List<Role> roles = new ArrayList<>();
//            for (String rolemap : rolesMap) {
//                roles.add(Role.valueOf(rolemap));
//            }
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                    username,
                    null
                    // roles.stream().map(authority -> new SimpleGrantedAuthority(authority.name())).collect(Collectors.toList())
            );
            return Mono.just(auth);
        } else {
            return Mono.empty();
        }
    }
}
