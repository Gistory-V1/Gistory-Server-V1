package gsm.gistory.domain.auth.service;

import gsm.gistory.domain.auth.dto.request.LoginRequestDto;
import gsm.gistory.domain.auth.dto.request.SignupRequestDto;
import gsm.gistory.domain.user.entity.User;
import gsm.gistory.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final NameService nameService;

    public void signup(SignupRequestDto request) {
        validateEmail(request.getEmail());
        checkEmailDuplicate(request.getEmail());

        String name = nameService.getNameFromEmail(request.getEmail());
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        User user = User.builder()
                .email(request.getEmail())
                .password(encodedPassword)
                .build();

        userRepository.save(user);
    }

    public void login(LoginRequestDto request, HttpSession session) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다."));

        validatePassword(request.getPassword(), user.getPassword());

        session.setAttribute("sessionId", user.getId());
    }

    public void logout(HttpSession session, String password) {
        Long userId = getSessionUserId(session);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        validatePassword(password, user.getPassword());

        session.invalidate();
    }

    public void deleteAccount(HttpSession session) {
        Long userId = getSessionUserId(session);
        userRepository.deleteById(userId);
        session.invalidate();
    }

    private void validateEmail(String email) {
        if (!email.endsWith("@gsm.hs.kr")) {
            throw new IllegalArgumentException("이메일 형식이 @gsm.hs.kr 이어야 합니다.");
        }
    }

    private void checkEmailDuplicate(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
    }

    private void validatePassword(String rawPassword, String encodedPassword) {
        if (!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }
    }

    private Long getSessionUserId(HttpSession session) {
        Long userId = (Long) session.getAttribute("sessionId");
        if (userId == null) {
            throw new IllegalStateException("로그인 상태가 아닙니다.");
        }
        return userId;
    }
}
