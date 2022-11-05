package com.cardenas.rest.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserPrincipal implements UserDetails {
    private String name;
    private String username;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority>authorities;

    public static UserPrincipal build(User user){
        List<GrantedAuthority> authorityList=
                user.getRoles().stream().map(rol->new SimpleGrantedAuthority(rol
                .name())).collect(Collectors.toList());
        return new UserPrincipal(user.getName(),user.getUsername(),user.getEmail(),user.getPassword(),authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
         return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}
