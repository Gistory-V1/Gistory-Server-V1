package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.request.LoginRequest;
import gsm.gistory.domain.auth.dto.response.LoginResponse;
import gsm.gistory.domain.auth.service.LoginService;
import gsm.gistory.global.security.jwt.TokenGenerator;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private AuthenticationManager authenticationManager;
    private TokenGenerator tokenGenerator;

    @Override
    public LoginResponse login(LoginRequest request) {
        // 로그인 로직
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());

        authenticationManager.authenticate(authenticationToken);

        // JWT 토큰 생성
        String accessToken = tokenGenerator.generateToken(request.getEmail()).getAccessToken();
        String refreshToken = tokenGenerator.generateToken(request.getEmail()).getRefreshToken();

        return new LoginResponse(accessToken, refreshToken);
    }
}

