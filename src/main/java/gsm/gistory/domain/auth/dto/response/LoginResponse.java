package gsm.gistory.domain.auth.dto.response;

import gsm.gistory.global.security.jwt.dto.JwtToken;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse {

    private String message;
    private JwtToken token;
}
