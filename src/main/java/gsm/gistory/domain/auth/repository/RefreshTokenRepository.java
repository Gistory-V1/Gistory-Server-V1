package gsm.gistory.domain.auth.repository;

import gsm.gistory.domain.auth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String email);
    Optional<RefreshToken> findTokenByEmail(String email);
}
