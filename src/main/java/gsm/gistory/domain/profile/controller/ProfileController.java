package gsm.gistory.domain.profile.controller;

import gsm.gistory.domain.profile.dto.response.ProfileResponse;
import gsm.gistory.domain.profile.service.ProfileService;
import gsm.gistory.global.security.jwt.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping
    public ResponseEntity<Object> getProfile(@RequestParam("name") String name) {
        try {
            ProfileResponse response = profileService.getProfile(name);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
