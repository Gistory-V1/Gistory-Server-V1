package gsm.gistory.domain.auth.service;

import gsm.gistory.domain.auth.dto.response.ReissueTokenResponsedto;

public interface ReissueService {
    ReissueTokenResponsedto reissue(String refreshToken);
}
