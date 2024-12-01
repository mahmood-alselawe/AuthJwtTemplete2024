package com.takarub.AuthJwtTemplate.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@ToString(exclude = "tokens")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_User")
public class User implements UserDetails {


    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;

    private String lastName;

    private String email;

    private String passWord;

    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Token> tokens;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ForgotPassword forgotPassword;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of(new SimpleGrantedAuthority(role.name()));
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return passWord;
    }

    @Override
    public String getUsername() {
        return email;
    }


}
