package gsm.gistory.domain.auth.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReissueTokenResponse {
    private String accessToken;
    private String accessTokenExpiresIn;
}
