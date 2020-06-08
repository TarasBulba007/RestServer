package com.tarasPlus.serverRest.model;

import lombok.*;

import javax.persistence.*;
import java.io.StringReader;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name="users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "birth_date", nullable = false)
    private Date birth_date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="user_role",
            joinColumns =@JoinColumn(name="user_Id", referencedColumnName ="id"),
            inverseJoinColumns = @JoinColumn(name="role_Id", referencedColumnName = "id")
    )
        private Set<Role> roles = new HashSet<>();


}
