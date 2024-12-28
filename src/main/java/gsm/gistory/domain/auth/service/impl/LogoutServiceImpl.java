package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.response.LogoutResponse;
import gsm.gistory.domain.auth.service.LogoutService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutServiceImpl implements LogoutService {

    private final JwtTokenProvider jwtProvider;

    @Override
    public LogoutResponse logout(String token) {
        if (!token.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "제목은 1 ~ 15글자여야 합니다.");
        }
        String accessToken = token.substring(7);

        if (!jwtProvider.validateToken(accessToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "제목은 1 ~ 15글자여야 합니다.");
        }

        return new LogoutResponse("로그아웃 성공");
    }
}
