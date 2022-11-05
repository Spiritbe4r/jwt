package com.cardenas.rest.service.impl;

import com.cardenas.rest.entity.User;
import com.cardenas.rest.entity.UserPrincipal;
import com.cardenas.rest.repository.UserRepository;
import com.cardenas.rest.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUsername(username).get();
        return UserPrincipal.build(user);
    }
}
