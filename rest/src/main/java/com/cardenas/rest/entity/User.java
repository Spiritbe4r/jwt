package com.cardenas.rest.entity;

import lombok.*;
import org.springframework.lang.Nullable;


import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
@Builder
public class User  implements Serializable {

    @Serial
    private static final long serialVersionUID = 8462972624453318610L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;

    private String email;
    @Column(length = 100)
    private String password;
    private Boolean locked;

    private Boolean enabled ;

    @ElementCollection
    private Set<UserRole> roles ;




    //@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //@JoinTable(name="user_roles",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name="role_id"))



    // NUEVO METODO
   /* public void addRole (Role role)
    {

        this.roles.add(role);
    }*/

}

