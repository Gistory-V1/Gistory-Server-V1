package gsm.gistory.domain.auth.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReissueTokenRequest {

    @NotBlank(message = "리프레시 토큰은 필수 항목입니다.")
    private String refreshToken;
}
