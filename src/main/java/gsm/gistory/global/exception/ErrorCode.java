package gsm.gistory.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404),             // 사용자 없음
    INVALID_SESSION(401),            // 세션 유효하지 않음
    INVALID_INPUT(400);              // 잘못된 요청 데이터

    private final int status;
}
