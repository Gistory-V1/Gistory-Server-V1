package gsm.gistory.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReissueTokenResponsedto {
    private final String accessToken;
    private final String accessTokenExpiresIn;
    private final String refreshToken;
    private final String refreshTokenExpiresIn;

}
