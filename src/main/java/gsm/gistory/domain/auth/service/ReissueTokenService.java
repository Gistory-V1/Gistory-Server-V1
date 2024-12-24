package gsm.gistory.domain.auth.service;

import gsm.gistory.domain.auth.dto.response.ReissueResponse;

public interface ReissueTokenService {
    ReissueResponse execute(String refreshToken);
}

