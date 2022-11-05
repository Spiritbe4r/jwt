package com.cardenas.rest.jwt;

import com.cardenas.rest.dtos.AuthenticationResponse;
import com.cardenas.rest.dtos.LoginRequest;
import com.cardenas.rest.dtos.RegisterDto;
import com.cardenas.rest.service.UserService;
import com.sun.source.util.JavacTask;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping(JwtResource.JWTS)
public class JwtResource {

    public static final String JWTS="/api/auth";

    public static final String LOGIN="/login";

    @Autowired
    private AuthenticationManager authenticationManager;

    private final UserService userService;


    @PostMapping("register")
    public ResponseEntity<Void> register(@RequestBody RegisterDto request) {
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    //@PreAuthorize("authenticated")
    @PostMapping(value=LOGIN)
    public ResponseEntity<AuthenticationResponse> login (@RequestBody LoginRequest loginRequest){

        var authenticationResponse= userService.loginUser(loginRequest);

        return new ResponseEntity<>(authenticationResponse, HttpStatus.OK);


    }
    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public String verify(){
        return "OK. permitido JWT";
    }
}
