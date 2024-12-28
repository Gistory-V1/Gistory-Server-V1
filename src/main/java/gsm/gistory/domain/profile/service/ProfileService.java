package gsm.gistory.domain.profile.service;

import gsm.gistory.domain.profile.dto.response.ProfileResponse;

public interface ProfileService {
    ProfileResponse getProfile(String name);
}
