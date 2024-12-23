package gsm.gistory.domain.profile.service;

import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.profile.dto.response.ProfileResponseDto;
import gsm.gistory.domain.user.entity.User;
import gsm.gistory.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public ProfileResponseDto getProfile(String name) {

        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        Long postsCount = postRepository.countByAuthorId(user.getId());

        Long likesCount = postRepository.sumLikesByAuthorId(user.getId());

        return ProfileResponseDto.builder()
                .userId(user.getId())
                .name(user.getName())
                .postsCount(postsCount)
                .likesCount(likesCount)
                .build();
    }
}
