package com.cardenas.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@Data
@AllArgsConstructor
public class AuthenticationResponse {
    private String authenticationToken;
    private String username;
    private Collection<? extends GrantedAuthority> authorities;
}
