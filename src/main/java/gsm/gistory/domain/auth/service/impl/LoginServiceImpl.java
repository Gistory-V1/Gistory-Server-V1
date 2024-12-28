package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.request.LoginRequest;
import gsm.gistory.domain.auth.dto.response.LoginResponse;
import gsm.gistory.domain.auth.service.LoginService;
import gsm.gistory.domain.auth.user.entity.User;
import gsm.gistory.domain.auth.user.repository.UserRepository;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResponse login(LoginRequest request) {
        if (!request.getEmail().endsWith("@gsm.hs.kr")) {
            throw new CustomException(ErrorCode.INVALID_EMAIL, "이메일은 '@gsm.hs.kr'로 끝나야 합니다.");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new CustomException(ErrorCode.INVALID_PASSWORD, "비밀번호가 잘못되었습니다.");
        }

        String accessToken = jwtTokenProvider.generateAccessToken(user.getEmail());
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return new LoginResponse(
                "로그인 성공",
                accessToken,
                refreshToken,
                jwtTokenProvider.getAccessTokenExpiration(accessToken),
                jwtTokenProvider.getRefreshTokenExpiration(refreshToken),
                user.getName()
        );
    }
}
