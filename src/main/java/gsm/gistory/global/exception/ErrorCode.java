package gsm.gistory.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),       // 사용자 없음
    INVALID_SESSION(401, "세션이 유효하지 않습니다."),      // 세션 유효하지 않음
    INVALID_INPUT(400, "잘못된 요청 데이터입니다."),        // 잘못된 요청 데이터
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다.");      // 게시글 없음

    private final int status;         // HTTP 상태 코드
    private final String message;     // 기본 에러 메시지
}
