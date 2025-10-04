package ogenblad.example.individuellUppgift.configuration;

import ogenblad.example.individuellUppgift.repository.DaoUser;
import ogenblad.example.individuellUppgift.security.AppUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailsConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DaoUser userRepo) {
        return username -> {
            AppUser appUser = userRepo.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

            return User.withUsername(appUser.getUsername())
                    .password(appUser.getPassword())
                    .authorities(appUser.getAuthorities())
                    .build();
        };
    }
}
