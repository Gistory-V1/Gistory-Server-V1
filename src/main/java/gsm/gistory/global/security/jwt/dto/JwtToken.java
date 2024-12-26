package gsm.gistory.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JwtToken {
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiresIn;
    private String refreshTokenExpiresIn;
}
