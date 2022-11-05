package com.cardenas.rest.dtos;

import com.cardenas.rest.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RegisterDto {

        private String username;
        private String email;
        private String password;

        private boolean enabled;

}
