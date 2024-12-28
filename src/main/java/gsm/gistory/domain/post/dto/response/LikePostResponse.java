package gsm.gistory.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class LikePostResponse {
    private String message;
    private Long likeCount;
    private int statusCode;
}
