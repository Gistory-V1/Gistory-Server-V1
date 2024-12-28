package gsm.gistory.domain.rank.controller;

import gsm.gistory.domain.rank.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rank-views")
@RequiredArgsConstructor
public class PostRankController {

    private final PostService postService;

    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getTop5PostsByViews() {
        List<Map<String, Object>> result = postService.getTop5PostsByViews();
        return ResponseEntity.ok(result);
    }
}
