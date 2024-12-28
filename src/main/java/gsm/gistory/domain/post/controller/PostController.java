package gsm.gistory.domain.post.controller;

import gsm.gistory.domain.post.dto.request.CreatePostRequest;
import gsm.gistory.domain.post.dto.response.CreatePostResponse;
import gsm.gistory.domain.post.dto.response.GetPostResponse;
import gsm.gistory.domain.post.service.CreatePostService;
import gsm.gistory.domain.post.service.GetPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final CreatePostService createPostService;
    private final GetPostService getPostService;

    @PostMapping("/create")
    public ResponseEntity<CreatePostResponse> createPost(
            @Valid @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CreatePostRequest createPostRequest) {
        CreatePostResponse response = createPostService.createPost(createPostRequest, authorizationHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<GetPostResponse> getPost(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("postId") Long postId
    ) {
        GetPostResponse response = getPostService.getPost(postId, authorization);
        return ResponseEntity.ok(response);
    }
}
