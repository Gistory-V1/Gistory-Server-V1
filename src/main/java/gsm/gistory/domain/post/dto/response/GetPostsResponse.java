package gsm.gistory.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPostsResponse {
    private Long postId;
    private String title;
    private String content;
    private Long likeCount;
    private Long views;
}
