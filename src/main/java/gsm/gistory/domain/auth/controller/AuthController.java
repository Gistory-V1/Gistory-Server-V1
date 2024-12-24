package gsm.gistory.domain.auth.controller;

import gsm.gistory.domain.auth.dto.request.LoginRequest;
import gsm.gistory.domain.auth.dto.request.SignUpRequest;
import gsm.gistory.domain.auth.dto.response.*;
import gsm.gistory.domain.auth.service.LoginService;
import gsm.gistory.domain.auth.service.LogoutService;
import gsm.gistory.domain.auth.service.ReissueTokenService;
import gsm.gistory.domain.auth.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LogoutService logoutService;
    private final ReissueTokenService reissueTokenService;
    private final SignUpService signUpService;
    private final LoginService loginService;


    @PostMapping("/signup")
    public SignUpResponse register(@RequestBody SignUpRequest request) {
        return signUpService.signUp(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return loginService.login(request);
    }

    @PostMapping("/reissue")
    public ReissueResponse reissueToken(@RequestHeader("refresh") String refreshToken) {
        return reissueTokenService.execute(refreshToken);
    }

    @DeleteMapping("/logout")
    public LogoutResponse logout(@RequestHeader("Authorization") String token) {
        return logoutService.logout(token);
    }
}
