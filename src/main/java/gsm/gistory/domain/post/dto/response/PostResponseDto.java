package gsm.gistory.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostResponseDto {
    private Long postId;
    private String title;
    private String content;
    private Long likeCount;
    private String author;
    private Long views;
    private String createdAt;
}
