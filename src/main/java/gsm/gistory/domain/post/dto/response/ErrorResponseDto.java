package gsm.gistory.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private String errorCode;
    private String message;
}
