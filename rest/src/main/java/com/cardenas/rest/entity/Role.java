package com.cardenas.rest.entity;

import com.cardenas.rest.enums.UserRoles;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name="roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @NotNull
    @Enumerated(EnumType.STRING)
    private UserRoles roleName;

    public Role(){

    }




}
