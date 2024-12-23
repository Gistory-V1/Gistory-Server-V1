package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.request.CreatePostRequestDto;
import gsm.gistory.domain.post.dto.response.PostResponseDto;
import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.user.entity.User;
import gsm.gistory.domain.user.service.UserService;
import gsm.gistory.global.exception.CustomException;
import gsm.gistory.global.exception.ErrorCode;
import gsm.gistory.global.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final SessionManager sessionManager;
    private final UserService userService;

    public void createPost(CreatePostRequestDto request, String sessionId) {

        Long userId = sessionManager.validateSession(sessionId);

        validatePostContent(request);

        User author = userService.findUserById(userId);

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .author(author.getName())
                .views(0L)
                .createdAt(LocalDateTime.now())
                .build();

        postRepository.save(post);
    }

    private void validatePostContent(CreatePostRequestDto request) {
        if (request.getTitle() == null || request.getTitle().length() < 1 || request.getTitle().length() > 15) {
            throw new CustomException(ErrorCode.INVALID_INPUT, "제목은 1자 이상 15자 이하로 입력해야 합니다.");
        }
        if (request.getContent() == null || request.getContent().length() > 300) {
            throw new CustomException(ErrorCode.INVALID_INPUT, "내용은 최대 300자까지 입력할 수 있습니다.");
        }
    }
    public PostResponseDto getPost(String sessionId, Long postId) {
        // 세션 검증
        sessionManager.validateSession(sessionId);

        // 게시글 조회
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND, "게시글을 찾을 수 없습니다."));

        // 조회수 증가
        post.setViews(post.getViews() + 1);
        postRepository.save(post);

        // Response 생성
        return PostResponseDto.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .author(post.getAuthor())
                .views(post.getViews())
                .createdAt(post.getCreatedAt().toString())
                .build();
    }
}
