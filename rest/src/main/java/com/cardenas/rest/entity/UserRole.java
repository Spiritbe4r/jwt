package com.cardenas.rest.entity;

import lombok.Getter;

@Getter
public enum UserRole {

    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");

    private String name;

    UserRole(String name) {
        this.name = name;
    }


    public String getName(){
        return this.name;
    }

}
