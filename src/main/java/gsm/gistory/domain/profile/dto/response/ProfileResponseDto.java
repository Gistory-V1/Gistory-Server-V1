package gsm.gistory.domain.profile.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileResponseDto {
    private Long userId;
    private String name;
    private Long postsCount;
    private Long likesCount;
}
