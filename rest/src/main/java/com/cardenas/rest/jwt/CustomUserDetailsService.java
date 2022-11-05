package com.cardenas.rest.jwt;

import com.cardenas.rest.entity.UserRole;
import com.cardenas.rest.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByUsername(username).orElseThrow
                (() -> new UsernameNotFoundException("User Not Found with -> username or email: " + username));
        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        return new org.springframework.security.core.userdetails.User
                (user.getUsername(), user.getPassword(), user.getEnabled(), true, true,
                        true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        return userRoles.stream().map(rol -> new SimpleGrantedAuthority(rol
                .name())).collect(Collectors.toList());
    }

}
