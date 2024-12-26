package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.request.SignUpRequest;
import gsm.gistory.domain.auth.dto.response.SignUpResponse;
import gsm.gistory.domain.auth.service.SignUpService;
import gsm.gistory.domain.auth.user.entity.User;
import gsm.gistory.domain.auth.user.repository.UserRepository;
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
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new CustomException(ErrorCode.USER_ALREADY_EXISTS);
        }

        User user = User.builder()
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();

        userRepository.save(user);

        return new SignUpResponse("회원가입 성공");
    }
}
