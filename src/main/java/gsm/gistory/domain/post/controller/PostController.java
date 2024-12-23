package gsm.gistory.domain.post.controller;

import gsm.gistory.domain.post.dto.request.CreatePostRequestDto;
import gsm.gistory.domain.post.dto.request.UpdatePostRequestDto;
import gsm.gistory.domain.post.dto.response.PostResponseDto;
import gsm.gistory.domain.post.dto.response.UpdatePostResponseDto;
import gsm.gistory.domain.post.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
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

    @GetMapping
    public ResponseEntity<PostResponseDto> getPost(
            @RequestHeader("sessionId") String sessionId,
            @RequestParam("postId") Long postId) {
        PostResponseDto response = postService.getPost(sessionId, postId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatePost(@RequestBody UpdatePostRequestDto request, HttpServletRequest httpRequest) {
        String sessionId = httpRequest.getHeader("sessionId");

        postService.updatePost(request, sessionId);

        return ResponseEntity.ok().body(
                new UpdatePostResponseDto("게시글 수정 성공", HttpStatus.OK.value())
        );
    }
}
