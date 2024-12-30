package gsm.gistory.domain.post.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DeletePostResponse {
    private final String message;
    private final int statusCode;
}
