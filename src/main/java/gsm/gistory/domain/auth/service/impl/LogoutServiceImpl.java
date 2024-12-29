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
            throw new CustomException(ErrorCode.INVALID_TOKEN, "토큰 형식이 잘못되었습니다. 'Bearer '로 시작해야 합니다.");
        }
        String accessToken = token.substring(7);

        if (!jwtProvider.validateToken(accessToken)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        return new LogoutResponse("로그아웃에 성공했습니다.");
    }
}
