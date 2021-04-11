package net.thetechstack.projectmybookcollection.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean public UserDetailsService userDetailsService() {
        return new ReaderDetailsService();
    } 
    @Bean public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    
    @Override
    protected void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity.formLogin().defaultSuccessUrl("/", true);
        httpSecurity.authorizeRequests().anyRequest().authenticated();
        //.loginPage("/login");
        //httpSecurity.formLogin()
          //      .loginPage("/login")
            //    .defaultSuccessUrl("/collection", true);
    }
}
