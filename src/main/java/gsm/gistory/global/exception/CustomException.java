package gsm.gistory.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String message;

    @Override
    public String getMessage() {
        return message;
    }
}
