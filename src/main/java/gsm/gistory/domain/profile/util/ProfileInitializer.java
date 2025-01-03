package gsm.gistory.domain.profile.util;

import gsm.gistory.domain.auth.util.StudentEmailMapper;
import gsm.gistory.domain.profile.entity.Profile;
import gsm.gistory.domain.profile.repository.ProfileRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProfileInitializer {

    private final ProfileRepository profileRepository;

    @PostConstruct
    public void initializeProfiles() {
        StudentEmailMapper.getEmailToNameMap().forEach((emailPrefix, name) -> {
            String email = emailPrefix + "@gsm.hs.kr";

            if (!profileRepository.existsByEmail(email)) {
                Profile profile = Profile.builder()
                        .email(email)
                        .name("NULL".equals(name) ? null : name)
                        .subCount(0L)
                        .build();

                profileRepository.save(profile);
            }
        });
    }
}

