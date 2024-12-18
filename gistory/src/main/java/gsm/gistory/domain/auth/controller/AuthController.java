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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponseDto> signup(@Valid @RequestBody SignupRequestDto request) {
        if (!request.getEmail().endsWith("@gsm.hs.kr")) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto("이메일 형식이 @gsm.hs.kr 이어야 합니다.", 400));
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword);

        authService.signup(request);

        return ResponseEntity.status(201)
                .body(new AuthResponseDto("회원가입 성공", 201));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto request, HttpSession session) {
        try {
            authService.login(request, session);
            return ResponseEntity.ok(new AuthResponseDto("로그인 성공", 200));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest()
                    .body(new AuthResponseDto("이메일 또는 비밀번호가 일치하지 않습니다.", 400));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponseDto> logout(@RequestBody LogoutRequestDto request, HttpSession session) {
        try {
            authService.logout(session, request.getPassword());
            return ResponseEntity.ok(new AuthResponseDto("로그아웃 성공", 200));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body(new AuthResponseDto(e.getMessage(), 400));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(401).body(new AuthResponseDto(e.getMessage(), 401));
        }
    }


    @DeleteMapping("/delete")
    public ResponseEntity<AuthResponseDto> deleteAccount(HttpSession session) {
        authService.deleteAccount(session);
        session.invalidate();
        return ResponseEntity.ok(new AuthResponseDto("회원탈퇴 성공", 200));
    }
}