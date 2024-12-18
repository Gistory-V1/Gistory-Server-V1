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

        String email = request.getEmail();

        String name = nameService.getNameFromEmail(email);

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

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

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 일치하지 않습니다.");
        }

        session.setAttribute("sessionId", user.getId());
    }

    public void logout(HttpSession session, String password) {
        Long userId = (Long) session.getAttribute("sessionId");
        if (userId == null) {
            throw new IllegalStateException("로그인 상태가 아닙니다.");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        session.invalidate();
    }


    public void deleteAccount(HttpSession session) {
        Long userId = (Long) session.getAttribute("sessionId");
        userRepository.deleteById(userId);
    }
}
