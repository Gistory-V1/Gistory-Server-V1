package gsm.gistory.domain.auth.controller;

import gsm.gistory.domain.auth.dto.request.LoginRequest;
import gsm.gistory.domain.auth.dto.request.SignUpRequest;
import gsm.gistory.domain.auth.dto.response.LoginResponse;
import gsm.gistory.domain.auth.dto.response.LogoutResponse;
import gsm.gistory.domain.auth.dto.response.SignUpResponse;
import gsm.gistory.domain.auth.service.LoginService;
import gsm.gistory.domain.auth.service.LogoutService;
import gsm.gistory.domain.auth.service.ReissueService;
import gsm.gistory.domain.auth.service.SignUpService;
import gsm.gistory.global.security.jwt.dto.ReissueTokenResponsedto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final SignUpService signUpService;
    private final LoginService signInService;
    private final ReissueService reissueService;
    private final LogoutService logoutService;

    @PostMapping("/signup")
    public SignUpResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return signUpService.signup(signUpRequest);
    }

    @PostMapping("/login")
    public LoginResponse signIn(@RequestBody LoginRequest loginRequest) {
        return signInService.login(loginRequest);
    }

    @PatchMapping("/reissue")
    public ReissueTokenResponsedto reissue(@RequestHeader("refreshToken") String refreshToken) {
        return reissueService.reissue(refreshToken);
    }

    @DeleteMapping("/logout")
    public LogoutResponse logout(@RequestHeader("Authorization") String token) {
        return logoutService.logout(token);
    }
}
