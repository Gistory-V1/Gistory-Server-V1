package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.service.ReissueService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.dto.ReissueTokenResponsedto;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReissueServiceImpl implements ReissueService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public ReissueTokenResponsedto reissue(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new CustomException(ErrorCode.INVALID_REFRESH_TOKEN, "유효하지 않은 refreshToken 입니다.");
        }

        String email = jwtTokenProvider.getEmailFromToken(refreshToken);
        String newAccessToken = jwtTokenProvider.generateAccessToken(email);
        String newRefreshToken = jwtTokenProvider.generateRefreshToken(email);

        return new ReissueTokenResponsedto(
                newAccessToken,
                jwtTokenProvider.getAccessTokenExpiration(newAccessToken),
                newRefreshToken,
                jwtTokenProvider.getRefreshTokenExpiration(newRefreshToken)
        );
    }

}
