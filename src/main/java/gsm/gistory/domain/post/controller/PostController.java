package gsm.gistory.domain.post.controller;

import gsm.gistory.domain.post.dto.request.CreatePostRequestDto;
import gsm.gistory.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@RequestBody CreatePostRequestDto request,
                                        @RequestHeader("sessionId") String sessionId) {
        postService.createPost(request, sessionId);
        return ResponseEntity.status(HttpStatus.CREATED).body("글 작성 성공");
    }
}
