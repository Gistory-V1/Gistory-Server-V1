package gsm.gistory.domain.profile.util;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProfileDataInitializerRunner implements CommandLineRunner {

    private final ProfileDataInitializer profileDataInitializer;

    @Override
    public void run(String... args) {
        profileDataInitializer.initializeProfiles();
    }
}

