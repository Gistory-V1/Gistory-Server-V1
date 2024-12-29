package gsm.gistory.domain.subs.service.impl;

import gsm.gistory.domain.profile.entity.Profile;
import gsm.gistory.domain.profile.repository.ProfileRepository;
import gsm.gistory.domain.subs.dto.response.UnsubscribeResponse;
import gsm.gistory.domain.subs.entity.Subscription;
import gsm.gistory.domain.subs.repository.SubscriptionRepository;
import gsm.gistory.domain.subs.service.UnsubscribeService;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UnsubscribeServiceImpl implements UnsubscribeService {

    private final SubscriptionRepository subscriptionRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    @Override
    public UnsubscribeResponse unsubscribe(String name, String authorization, boolean subClick) {
        Subscription subscription = subscriptionRepository.findByName(name)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, "구독 대상이 존재하지 않습니다."));

        if (!subClick) {
            subscription.decrementSubCount();
            subscriptionRepository.save(subscription);

            Profile profile = profileRepository.findByName(name)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, "프로필을 찾을 수 없습니다."));
            profile.decrementSubCount();
            profileRepository.save(profile);

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
