package gsm.gistory.domain.profile.util;

import gsm.gistory.domain.profile.entity.Profile;
import gsm.gistory.domain.profile.repository.ProfileRepository;
import gsm.gistory.domain.auth.util.StudentEmailMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProfileDataInitializer {

    private final ProfileRepository profileRepository;

    public void initializeProfiles() {
        StudentEmailMapper.getEmailToNameMap().forEach((emailPrefix, name) -> {
            if (!name.equals("NULL")) {
                String email = emailPrefix + "@gsm.hs.kr";

                if (!profileRepository.existsByEmail(email)) {
                    Profile profile = Profile.builder()
                            .name(name)
                            .email(email)
                            .build();
                    profileRepository.save(profile);
                }
            }
        });
    }
}
