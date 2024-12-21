package gsm.gistory.global.session;

import gsm.gistory.domain.auth.repository.SessionRepository;
import gsm.gistory.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static gsm.gistory.global.exception.ErrorCode.INVALID_SESSION;

@Component
@RequiredArgsConstructor
public class SessionManager {

    private final SessionRepository sessionRepository;

    public void createSession(String sessionId, Long userId) {
        sessionRepository.save(sessionId, userId);
    }

    public Long validateSession(String sessionId) {
        Long userId = sessionRepository.findBySessionId(sessionId);
        if (userId == null) {
            throw new CustomException(INVALID_SESSION, "세션이 유효하지 않습니다.");
        }
        return userId;
    }

    public void deleteSession(String sessionId) {
        sessionRepository.delete(sessionId);
    }
}
