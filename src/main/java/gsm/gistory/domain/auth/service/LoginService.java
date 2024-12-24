package gsm.gistory.domain.auth.service;

import gsm.gistory.domain.auth.dto.request.LoginRequest;
import gsm.gistory.domain.auth.dto.response.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}


