package ru.itmo.web.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.web.model.Status;
import ru.itmo.web.model.User;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    static final String ROLE_PREFIX = "ROLE_";

    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(ROLE_PREFIX + user.getRole().getName().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !Status.LOGIN_EXPIRED.equals(user.getStatus());
    }

    @Override
    public boolean isAccountNonLocked() {
        return !Status.BLOCKED.equals(user.getStatus());
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !Status.PASSWORD_EXPIRED.equals(user.getStatus());
    }

    @Override
    public boolean isEnabled() {
        return Status.OK.equals(user.getStatus());
    }
}
