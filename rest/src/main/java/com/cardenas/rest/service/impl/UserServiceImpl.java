package com.cardenas.rest.service.impl;

import com.cardenas.rest.dtos.AuthenticationResponse;
import com.cardenas.rest.dtos.JwtResponse;
import com.cardenas.rest.dtos.LoginRequest;
import com.cardenas.rest.dtos.RegisterDto;
import com.cardenas.rest.entity.User;
import com.cardenas.rest.entity.UserRole;
import com.cardenas.rest.jwt.JwtProvider;
import com.cardenas.rest.repository.UserRepository;
import com.cardenas.rest.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;




    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    @Override
    public void register(RegisterDto registerRequest) {


            User user =new User();
            user.setUsername(registerRequest.getUsername());
            user.setPassword(encodePassword(registerRequest.getPassword()));
            user.setEmail(registerRequest.getEmail());
            user.setRoles(Set.of(UserRole.ROLE_USER));
//            Role roleuser=roleRepository.findByRoleName("USER");
//            user.addRole(roleuser);

            user.setEnabled(true);
            userRepository.save(user);


    }

    @Override
    public AuthenticationResponse loginUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);

        UserDetails userDetails= (UserDetails)authentication.getPrincipal();

        return new AuthenticationResponse(jwt,userDetails.getUsername(),userDetails.getAuthorities());
    }




    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user) {
        User userObj =getUserById(user.getId());
        userObj.setUsername(user.getUsername());
        userObj.setName(user.getName());
        userObj.setEmail(user.getEmail());
        userRepository.save(userObj);

        return userObj;
    }

    @Override
    public void deleteUser(Long id) {
         userRepository.deleteById(id);
    }

    @Override
    public Optional<User> getByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
