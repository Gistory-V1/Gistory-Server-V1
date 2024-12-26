package gsm.gistory.global.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;
    private String refreshToken;
    private LocalDateTime accessTokenExpiresIn;
    private LocalDateTime refreshTokenExpiresIn;
}