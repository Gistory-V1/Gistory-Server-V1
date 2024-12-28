package gsm.gistory.global.security.jwt.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String customMessage;

    public CustomException(ErrorCode errorCode, String customMessage) {
        super(customMessage);
        this.errorCode = errorCode;
        this.customMessage = customMessage;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }

    @Override
    public String getMessage() {
        return customMessage;
    }
}
