package gsm.gistory.domain.post.dto.request;

import lombok.Getter;

@Getter
public class LikePostRequest {
    private Long postId;
    private boolean likeClick;
}
