package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.response.LogoutResponse;
import gsm.gistory.domain.auth.service.LogoutService;
import gsm.gistory.global.security.jwt.TokenGenerator;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {

    private TokenGenerator tokenGenerator;

    @Override
    public LogoutResponse logout(String token) {
        tokenGenerator.invalidateToken(token);

        return new LogoutResponse("로그아웃 성공");
    }
}

