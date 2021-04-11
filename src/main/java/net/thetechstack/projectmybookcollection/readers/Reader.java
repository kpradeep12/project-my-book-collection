package net.thetechstack.projectmybookcollection.readers;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity @Data 
@Table(uniqueConstraints = @UniqueConstraint(name = "unq_user", columnNames = {"username"}))
public class Reader implements UserDetails {
    @Id String id;
    String username, password, firstName, lastName, email;
    boolean accountLocked;
    
    @OneToMany(mappedBy = "reader")
    private Set<net.thetechstack.projectmybookcollection.collections.Collection> bookCollection = new HashSet<>();
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "READ", () -> "WRITE");
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
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
