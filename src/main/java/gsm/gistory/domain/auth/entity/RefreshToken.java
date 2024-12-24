package gsm.gistory.domain.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "refresh_token", indexes = {@Index(name = "idx_email", columnList = "email")})
public class RefreshToken {

    @Id
    private String email;

    private String token;

    private int ttl;

    protected RefreshToken() {
    }
}
