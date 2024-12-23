package gsm.gistory.domain.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdatePostRequestDto {
    private Long postId;
    private String title;
    private String content;
}
