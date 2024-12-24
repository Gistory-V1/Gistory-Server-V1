package gsm.gistory.domain.auth.service;

import gsm.gistory.domain.auth.dto.response.LogoutResponse;

public interface LogoutService {
    LogoutResponse logout(String token);
}

