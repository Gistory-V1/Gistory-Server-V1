package gsm.gistory.domain.profile.service.impl;

import gsm.gistory.domain.profile.dto.response.ProfileResponse;
import gsm.gistory.domain.profile.entity.Profile;
import gsm.gistory.domain.profile.repository.ProfileRepository;
import gsm.gistory.domain.profile.service.ProfileService;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public ProfileResponse getProfile(String name) {
        Profile profile = profileRepository.findByName(name)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND, "사용자를 찾을 수 없습니다."));

        long postsCount = profile.getPosts().size();
        long likesCount = profile.getPosts().stream().mapToLong(post -> post.getLikeCount()).sum();

        return ProfileResponse.builder()
                .userId(profile.getId())
                .name(profile.getName())
                .postsCount(postsCount)
                .likesCount(likesCount)
                .build();
    }
}
