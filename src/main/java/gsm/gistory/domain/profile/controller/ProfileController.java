package gsm.gistory.domain.profile.controller;

import gsm.gistory.domain.profile.dto.response.ProfileResponseDto;
import gsm.gistory.domain.profile.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<?> getProfile(@RequestHeader("name") String name) {
        try {
            ProfileResponseDto response = profileService.getProfile(name);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.");
        }
    }
}
