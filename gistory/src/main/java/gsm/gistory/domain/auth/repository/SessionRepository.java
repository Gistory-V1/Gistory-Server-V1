package gsm.gistory.domain.auth.repository;


import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class SessionRepository {

    private final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public void save(String sessionId, Long userId) {
        sessionStore.put(sessionId, userId);
    }

    public Long findBySessionId(String sessionId) {
        return sessionStore.get(sessionId);
    }

    public void delete(String sessionId) {
        sessionStore.remove(sessionId);
    }
}
