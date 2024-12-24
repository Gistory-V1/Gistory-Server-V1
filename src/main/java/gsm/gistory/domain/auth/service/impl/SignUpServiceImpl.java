package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.request.SignUpRequest;
import gsm.gistory.domain.auth.dto.response.SignUpResponse;
import gsm.gistory.domain.auth.service.SignUpService;
import gsm.gistory.global.error.GlobalException;
import gsm.gistory.global.member.entity.Member;
import gsm.gistory.global.member.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignUpServiceImpl implements SignUpService {

    private MemberRepository memberRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public SignUpResponse signUp(SignUpRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new GlobalException("이미 등록된 이메일입니다.");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword());
        Member member = new Member(request.getEmail(), encodedPassword);
        memberRepository.save(member);

        return new SignUpResponse("회원가입 성공");
    }
}

