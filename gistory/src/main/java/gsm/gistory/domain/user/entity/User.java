package gsm.gistory.domain.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@gsm\\.hs\\.kr$",
            message = "이메일은 @gsm.hs.kr 도메인으로 끝나야 합니다."
    )
    private String email;

    @Column(nullable = false)
    private String password;
    private String name;
}

