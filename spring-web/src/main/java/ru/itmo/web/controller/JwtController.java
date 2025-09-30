package ru.itmo.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itmo.web.config.JwtHelper;
import ru.itmo.web.security.Authorities;
import ru.itmo.web.security.dto.AuthorizedUser;

@Slf4j
@RestController
@RequiredArgsConstructor
public class JwtController {

    private final JwtHelper jwtHelper;
    private final Authorities authorities;

    @PostMapping("/jwt")
    public String jwt(@RequestBody AuthorizedUser user) {
        return jwtHelper.generateToken("me", user.getLogin(), user.getRights());
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/secured/jwt")
    public String securedJwt() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof PreAuthenticatedAuthenticationToken authToken
                && authToken.getPrincipal() instanceof AuthorizedUser authorizedUser) {
            return "%s is %s".formatted(authorizedUser.getLogin(), authorities.isAdmin() ? "admin" : "not admin");
        }
        return "NoUser";
    }
}

