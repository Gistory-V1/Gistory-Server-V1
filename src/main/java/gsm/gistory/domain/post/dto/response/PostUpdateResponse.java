package gsm.gistory.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostUpdateResponse {
    private String message;
    private int statusCode;
}
