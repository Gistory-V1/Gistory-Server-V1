package gsm.gistory.domain.auth.service;

import gsm.gistory.global.security.jwt.dto.ReissueTokenResponsedto;

public interface ReissueService {
    ReissueTokenResponsedto reissue(String refreshToken);
}
