package se.johan.ituppgift.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.johan.ituppgift.LoggingComponent;
import se.johan.ituppgift.model.AppUser;
import se.johan.ituppgift.repository.AppUserRepository;

import java.util.List;

/**
 * Service som laddar användardetaljer för Spring Security autentisering.
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    private final LoggingComponent loggingComponent;

    /**
     * Konstruktor för MyUserDetailsService.
     *
     * @param appUserRepository Repository för AppUser-entiteter.
     * @param loggingComponent  Komponent för loggning.
     */
    public MyUserDetailsService(AppUserRepository appUserRepository, LoggingComponent loggingComponent) {
        this.appUserRepository = appUserRepository;
        this.loggingComponent = loggingComponent;
    }

    /**
     * Hämtar användardetaljer baserat på användarnamn.
     *
     * @param username Användarnamnet som ska laddas.
     * @return UserDetails-objekt med användarinformation.
     * @throws UsernameNotFoundException Om användaren inte finns.
     */
    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            loggingComponent.logError("Användare med användarnamn '" + username + "' hittades inte");
            throw new UsernameNotFoundException("Användare med användarnamn '" + username + "' hittades inte");
        }

        return new org.springframework.security.core.userdetails.User(
                appUser.getUsername(),
                appUser.getPassword(),
                true, true, true, true,
                List.of(new SimpleGrantedAuthority("ROLE_" + appUser.getRole()))
        );
    }
}
