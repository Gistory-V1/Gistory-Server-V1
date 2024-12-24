package gsm.gistory.domain.auth.service.impl;

import gsm.gistory.domain.auth.dto.response.ReissueResponse;
import gsm.gistory.domain.auth.service.ReissueTokenService;
import gsm.gistory.global.security.jwt.TokenGenerator;
import org.springframework.stereotype.Service;

@Service
public class ReissueTokenServiceImpl implements ReissueTokenService {

    private TokenGenerator tokenGenerator;

    @Override
    public ReissueResponse execute(String refreshToken) {
        String newAccessToken = tokenGenerator.generateToken(refreshToken).getAccessToken();

        return new ReissueResponse(newAccessToken);
    }
}

