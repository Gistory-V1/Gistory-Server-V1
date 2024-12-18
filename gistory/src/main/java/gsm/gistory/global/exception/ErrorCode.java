package gsm.gistory.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404),
    INVALID_SESSION(401);

    private final int status;
}
