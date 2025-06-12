package se.johan.ituppgift.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.johan.ituppgift.model.AppUser;

/**
 * Repository för AppUser. Hanterar CRUD och sökning på användarnamn.
 */
@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    /**
     * Hittar användare med angivet användarnamn.
     *
     * @param username Användarnamn att söka efter
     * @return AppUser med matchande användarnamn, eller null om ingen hittas
     */
    AppUser findByUsername(String username);
}
