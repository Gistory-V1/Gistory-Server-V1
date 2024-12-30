package gsm.gistory.domain.post.controller;

import gsm.gistory.domain.post.dto.request.CreatePostRequest;
import gsm.gistory.domain.post.dto.request.LikePostRequest;
import gsm.gistory.domain.post.dto.request.PostUpdateRequest;
import gsm.gistory.domain.post.dto.response.*;
import gsm.gistory.domain.post.service.*;
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
    private final PostUpdateService postUpdateService;
    private final LikePostService likePostService;
    private final UnlikePostService unlikePostService;
    private final DeletePostService deletePostService;


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

    @PostMapping("/update")
    public ResponseEntity<PostUpdateResponse> updatePost(
            @RequestBody PostUpdateRequest request,
            @RequestHeader("Authorization") String authorization) {
        PostUpdateResponse response = postUpdateService.updatePost(request, authorization);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deletePost(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("postId") Long postId) {

        deletePostService.deletePost(postId, authorization);

        return ResponseEntity.ok().body(new DeletePostResponse("글 삭제 성공", 200));
    }
    @PostMapping("/like")
    public ResponseEntity<LikePostResponse> likePost(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("postId") Long postId,
            @RequestBody @Valid LikePostRequest request) {

        LikePostResponse response = likePostService.likePost(postId, request.isLikeClick(), authorization);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/like/cancel")
    public ResponseEntity<LikePostResponse> unlikePost(
            @RequestHeader("Authorization") String authorization,
            @RequestParam("postId") Long postId) {

        LikePostResponse response = unlikePostService.unlikePost(postId, authorization);
        return ResponseEntity.ok(response);
    }
}
