package gsm.gistory.domain.auth.controller;

import gsm.gistory.domain.auth.dto.request.LoginRequestDto;
import gsm.gistory.domain.auth.dto.request.LogoutRequestDto;
import gsm.gistory.domain.auth.dto.request.SignupRequestDto;
import gsm.gistory.domain.auth.dto.response.AuthResponseDto;
import gsm.gistory.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody SignupRequestDto request) {
        authService.signup(request);
        return ResponseEntity.status(201)
                .body(new AuthResponseDto("회원가입 성공", 201));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request, HttpSession session) {
        authService.login(request, session);
        return ResponseEntity.ok(new AuthResponseDto("로그인 성공", 200));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponseDto> logout(@RequestBody LogoutRequestDto request, HttpSession session) {
        authService.logout(session, request.getPassword());
        return ResponseEntity.ok(new AuthResponseDto("로그아웃 성공", 200));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<AuthResponseDto> deleteAccount(HttpSession session) {
        authService.deleteAccount(session);
        return ResponseEntity.ok(new AuthResponseDto("회원탈퇴 성공", 200));
    }
}
