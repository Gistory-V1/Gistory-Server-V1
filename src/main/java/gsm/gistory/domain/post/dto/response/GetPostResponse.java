package gsm.gistory.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPostResponse {
    private Long postId;
    private String title;
    private String content;
    private Long likeCount;
    private String name;
    private Long views;
    private String createdAt;
}
