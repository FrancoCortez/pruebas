package cl.pim.auth.utils;

import cl.pim.auth.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Component
@Slf4j
public class JWTUtils implements Serializable {
    private final String secret = "a34jnf04iti9432mngk29rig94kf0laksdjlkasjdlkasd";
    private final String expirationTime = "28800000";


    Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secret).build().parseClaimsJws(token).getBody();
    }

    String getUsernameFromToken(String token) {
        return getAllClaimsFromToken(token).getSubject();
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(User user) {
        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> claims = new HashMap<>();
//        claims.put("role", user.getRoles().stream().map(Role::getCode).collect(Collectors.toList()));
//        claims.put("enable", user.getEnabled());
//        claims.putAll(mapper.convertValue(user, Map.class));
        return doGenerateToken(mapper.convertValue(user, Map.class), user);
    }

    private String doGenerateToken(Map<String, Object> claims, User user) {
        long expirationTimeLong = Long.parseLong(expirationTime);
        final Date createdDate = new Date();
        final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);
        Key key = Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    Boolean validateToken(String token) {
        return !isTokenExpired(token);
    }
}
