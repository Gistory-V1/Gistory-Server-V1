package gsm.gistory.domain.subs.service.impl;

import gsm.gistory.domain.subs.dto.response.SubscribeResponse;
import gsm.gistory.domain.subs.entity.Subscription;
import gsm.gistory.domain.subs.repository.SubscriptionRepository;
import gsm.gistory.domain.subs.service.SubscribeService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SubscribeServiceImpl implements SubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public SubscribeResponse subscribe(String name, String authorization, boolean subClick) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        String token = authorization.substring(7);
        jwtTokenProvider.validateToken(token);

        Subscription subscription = subscriptionRepository.findByName(name)
                .orElseGet(() -> Subscription.builder()
                        .name(name)
                        .subCount(0L)
                        .build());

        if (subClick) {
            subscription.incrementSubCount();
        } else {
            subscription.decrementSubCount();
        }

        subscriptionRepository.save(subscription);

        return SubscribeResponse.builder()
                .message("구독 성공")
                .subCount(subscription.getSubCount())
                .statusCode(200)
                .build();
    }
}