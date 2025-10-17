package org.kariev.inventorymgtsystem.security;

import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.kariev.inventorymgtsystem.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "userId")
@ToString(exclude = "password")
public final class AuthUser implements UserDetails, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final UUID userId;
    private final String email;
    private final String password;
    private final boolean enabled;
    private final boolean accountNonLocked;
    private final boolean accountNonExpired;
    private final boolean credentialsNonExpired;
    private final Collection<? extends GrantedAuthority> authorities;

    public AuthUser(User user) {
        Objects.requireNonNull(user, "user must not be null");
        this.userId = user.getId();
        this.email = user.getEmail() == null ? null : user.getEmail().trim().toLowerCase();
        this.password = user.getPassword();
        this.enabled = true;
        this.accountNonLocked = true;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;

        String roleName = (user.getRole() != null ? user.getRole().name() : "USER");
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleName));
    }

    @Override public String getUsername() { return email; }
    @Override public boolean isEnabled() { return enabled; }
    @Override public boolean isAccountNonLocked() { return accountNonLocked; }
    @Override public boolean isAccountNonExpired() { return accountNonExpired; }
    @Override public boolean isCredentialsNonExpired() { return credentialsNonExpired; }
}
