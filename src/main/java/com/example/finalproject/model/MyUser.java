package com.example.finalproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Email
    @Column(unique = true)
    private String email;
    private String password;
    @Pattern(regexp = "ADMIN||PROVIDER||USER")
    private String role;
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn(name = "userId")
    private Profile profile;
   /* @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<ServiceDetailes> serviceDetailes;

    */
   @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
   private List<Work> works;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
    private List<ServiceDetailes> serviceDetailes;

    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Boolean isEnabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
