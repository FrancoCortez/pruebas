package cl.pim.auth.service.impl;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.model.User;
import cl.pim.auth.repository.UserRepository;
import cl.pim.auth.service.AuthService;
import cl.pim.auth.shared.enumes.BasicStatusEnum;
import cl.pim.auth.utils.JWTUtils;
import cl.pim.auth.utils.PBKDF2Encoder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthImplService implements AuthService {

    private final UserRepository userRepository;
    private final JWTUtils jwtUtils;
    private final PBKDF2Encoder pbkdf2Encoder;

    public Mono<User> create(User user) {
        user.setId(UUID.randomUUID().toString());
        user.setStatus(BasicStatusEnum.ENABLED);
        user.setPassword(this.pbkdf2Encoder.encode(user.getPassword()));
        user.setIsNew(true);
        return this.userRepository.save(user);
    }

    public Mono<User> login(LoginResourceDto item) {
        return this.userRepository.findByUsername(item.getUsername())
                .map(user -> {
                    if (this.pbkdf2Encoder.encode(item.getPassword()).equals(user.getPassword())) {
                        String token = jwtUtils.generateToken(user);
                        log.info(token);
                    }
                    return user;
                });
    }

}
