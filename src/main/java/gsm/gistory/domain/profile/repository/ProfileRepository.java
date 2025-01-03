package gsm.gistory.domain.profile.repository;

import gsm.gistory.domain.profile.entity.Profile;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByName(String name);

    @Query("SELECT p FROM Profile p WHERE p.subCount IS NOT NULL ORDER BY p.subCount DESC")
    List<Profile> findTop5BySubCount(Pageable pageable);
    boolean existsByEmail(String email);
}
