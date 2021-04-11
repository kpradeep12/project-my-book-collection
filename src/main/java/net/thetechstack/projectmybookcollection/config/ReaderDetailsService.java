package net.thetechstack.projectmybookcollection.config;

import net.thetechstack.projectmybookcollection.readers.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ReaderDetailsService implements UserDetailsService {
    @Autowired
    ReaderRepository readerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return readerRepository.findByUsername(s).stream()
                .filter(r -> r.getUsername().equals(s))
                .findFirst()
                .map(r -> User.builder()
                        .username(r.getUsername())
                        .password(r.getPassword())
                        .authorities(r.getAuthorities())
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("User " + s + " not found!"));
    }
}
