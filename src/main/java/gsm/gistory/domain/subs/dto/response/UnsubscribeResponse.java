package gsm.gistory.domain.subs.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UnsubscribeResponse {
    private String message;
    private Long subCount;
    private int statusCode;
}