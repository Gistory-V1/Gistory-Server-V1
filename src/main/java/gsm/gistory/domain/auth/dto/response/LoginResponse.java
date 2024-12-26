package gsm.gistory.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponse {
    private String message;
    private String accessToken;
    private String refreshToken;
    private String accessTokenExpiresIn;
    private String refreshTokenExpiresIn;
    private String name;
}
