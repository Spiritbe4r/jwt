package com.cardenas.rest.dtos;

import lombok.Getter;

@Getter
public class JwtResponse {

    private String jwt;
    private Long id;
    private String username;
    private String email;
}
