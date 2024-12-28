package gsm.gistory.domain.post.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreatePostRequest {

    @NotBlank(message = "제목은 필수 입력값입니다.")
    @Size(min = 1, max = 15, message = "제목은 1 ~ 15글자여야 합니다.")
    private String title;

    @NotBlank(message = "내용은 필수 입력값입니다.")
    @Size(min = 1, max = 500, message = "내용은 1 ~ 500자여야 합니다.")
    private String content;
}
