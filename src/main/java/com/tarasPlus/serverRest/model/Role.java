package com.tarasPlus.serverRest.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role", nullable = false)
    private String role;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "roles")
//    private Set<User> users = new HashSet<>();

    public Role() {
        this.role = "USER";
    }


}