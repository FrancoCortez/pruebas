package cl.pim.auth.router;

import cl.pim.auth.dto.auth.LoginResourceDto;
import cl.pim.auth.dto.auth.LoginResponseResourceDto;
import cl.pim.auth.dto.auth.NewUserResourceDto;
import cl.pim.auth.dto.auth.UserResourceDto;
import cl.pim.auth.handler.AuthHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthHandler authHandler;

    @PostMapping
    public Mono<UserResourceDto> create(@Valid @RequestBody final NewUserResourceDto item) {
        return this.authHandler.create(item);
    }

    @PostMapping(value = "/login")
    public Mono<LoginResponseResourceDto> login(@Valid @RequestBody final LoginResourceDto item) {
        return this.authHandler.login(item);
    }

    @GetMapping(value = "/find/user/{id}")
    public Mono<UserResourceDto> findById(@PathVariable @NotNull final String id) {
        return this.authHandler.findById(id);
    }

}
