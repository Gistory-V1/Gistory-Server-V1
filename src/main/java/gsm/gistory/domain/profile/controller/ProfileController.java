package gsm.gistory.domain.profile.controller;

import gsm.gistory.domain.profile.dto.response.ProfileResponse;
import gsm.gistory.domain.profile.service.ProfileService;
import gsm.gistory.global.security.jwt.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Object> getProfile(@RequestHeader("name") String name) {
        try {
            ProfileResponse response = profileService.getProfile(name);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
