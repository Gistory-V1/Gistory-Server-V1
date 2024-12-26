package gsm.gistory.domain.auth.service;

import gsm.gistory.domain.auth.dto.request.SignUpRequest;
import gsm.gistory.domain.auth.dto.response.SignUpResponse;

public interface SignUpService {
    SignUpResponse signup(SignUpRequest signUpRequest);
}
