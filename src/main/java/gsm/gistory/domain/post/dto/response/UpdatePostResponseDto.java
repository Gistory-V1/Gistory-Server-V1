package gsm.gistory.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdatePostResponseDto {
    private String message;
    private int statusCode;
}
