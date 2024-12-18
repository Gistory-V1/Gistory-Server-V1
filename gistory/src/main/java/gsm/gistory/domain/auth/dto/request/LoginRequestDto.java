package gsm.gistory.domain.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "이메일을 입력해주세요.")
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@gsm\\.hs\\.kr$",
            message = "이메일은 @gsm.hs.kr 도메인만 허용됩니다."
    )
    private final String email;

    @NotBlank(message = "비밀번호를 입력해주세요.")
    private final String password;
}
