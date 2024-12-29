package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.request.SignUpRequest;
import gsm.gistory.domain.auth.dto.response.SignUpResponse;
import gsm.gistory.domain.auth.service.SignUpService;
import gsm.gistory.domain.auth.user.entity.User;
import gsm.gistory.domain.auth.user.repository.UserRepository;
import gsm.gistory.domain.auth.util.StudentEmailMapper;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse signup(SignUpRequest signUpRequest) {
        if (!signUpRequest.getEmail().endsWith("@gsm.hs.kr")) {
            throw new CustomException(ErrorCode.INVALID_EMAIL, "이메일은 '@gsm.hs.kr'로 끝나야 합니다.");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS, "이미 가입된 사용자입니다.");
        }

        String name = StudentEmailMapper.getNameByEmail(signUpRequest.getEmail());
        if ("Unknown".equals(name)) {
            throw new CustomException(ErrorCode.INVALID_EMAIL, "유효하지 않은 이메일입니다.");
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .name(name)
                .build();
        userRepository.save(user);

        return new SignUpResponse("회원가입이 성공적으로 완료되었습니다.");
    }
}
