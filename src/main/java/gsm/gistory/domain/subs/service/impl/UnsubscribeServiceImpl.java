package gsm.gistory.domain.subs.service.impl;

import gsm.gistory.domain.subs.dto.response.UnsubscribeResponse;
import gsm.gistory.domain.subs.entity.Subscription;
import gsm.gistory.domain.subs.repository.SubscriptionRepository;
import gsm.gistory.domain.subs.service.UnsubscribeService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UnsubscribeServiceImpl implements UnsubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UnsubscribeResponse unsubscribe(String name, String authorization, boolean subClick) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        String token = authorization.substring(7);
        jwtTokenProvider.validateToken(token);

        Subscription subscription = subscriptionRepository.findByName(name)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REQUEST, "구독 대상이 존재하지 않습니다."));

        if (!subClick) {
            subscription.decrementSubCount();
            subscriptionRepository.save(subscription);
        } else {
            throw new CustomException(ErrorCode.INVALID_REQUEST, "구독 취소 요청이 잘못되었습니다.");
        }

        return UnsubscribeResponse.builder()
                .message("구독 취소 성공")
                .subCount(subscription.getSubCount())
                .statusCode(200)
                .build();
    }
}
