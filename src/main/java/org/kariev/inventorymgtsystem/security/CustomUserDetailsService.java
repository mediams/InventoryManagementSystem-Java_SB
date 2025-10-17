package org.kariev.inventorymgtsystem.security;

import lombok.RequiredArgsConstructor;
import org.kariev.inventorymgtsystem.models.User;
import org.kariev.inventorymgtsystem.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || (username = username.trim()).isEmpty()) {
            throw new UsernameNotFoundException("Bad credentials");
        }

        String normalized = username.toLowerCase();
        User user = repository.findByEmail(normalized)
                .orElseThrow(() -> new UsernameNotFoundException("Bad credentials"));

        return new AuthUser(user);
    }
}
