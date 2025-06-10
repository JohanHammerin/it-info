package se.johan.ituppgift.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.repository.AppUserRepository;
import se.johan.ituppgift.exception.UserNotFoundException;
import se.johan.ituppgift.model.AppUser;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;
    private LoggingComponent loggingComponent;

    public MyUserDetailsService(AppUserRepository appUserRepository, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.loggingComponent = loggingComponent;
    }

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            loggingComponent.logError("Användaren hittades inte");
            throw new UserNotFoundException("Användaren hittades inte");
        }

        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                true, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );

    }
}
