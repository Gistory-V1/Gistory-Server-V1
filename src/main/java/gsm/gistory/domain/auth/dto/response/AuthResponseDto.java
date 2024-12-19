package gsm.gistory.domain.auth.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AuthResponseDto {
    private final String message;
    private final int statusCode;
}
