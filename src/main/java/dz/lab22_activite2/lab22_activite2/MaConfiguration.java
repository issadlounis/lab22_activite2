package dz.lab22_activite2.lab22_activite2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MaConfiguration {
    @Bean
    public BCryptPasswordEncoder pwdEncoder() { return new BCryptPasswordEncoder(); }
    @Bean
    public UserDetailsService userDetailsService(BCryptPasswordEncoder bcrypt) {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername("user")
                .password(bcrypt.encode("123")).roles("USER").build());
        manager.createUser(User.withUsername("admin")
                .password(bcrypt.encode("987")).roles("USER", "ADMIN").build());
        return manager;
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeRequests(request -> request
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/portail").hasRole("USER")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                );
        http.formLogin(AbstractAuthenticationFilterConfigurer::permitAll);
        http.logout(LogoutConfigurer::permitAll);
        return http.build();
    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/images/**", "/js/**");
    }

}
