package gsm.gistory.domain.rank.controller;

import gsm.gistory.domain.rank.service.ProfileRankService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/rank-subs")
public class ProfileRankController {

    private final ProfileRankService profileRankService;

    @GetMapping
    public ResponseEntity<List<ProfileRankService.RankResponse>> getTop5Subscribers() {
        List<ProfileRankService.RankResponse> top5Subscribers = profileRankService.getTop5Subscribers();
        return ResponseEntity.ok(top5Subscribers);
    }
}
