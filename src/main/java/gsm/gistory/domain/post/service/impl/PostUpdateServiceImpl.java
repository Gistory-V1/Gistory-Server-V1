package gsm.gistory.domain.post.service.impl;

import gsm.gistory.domain.post.dto.request.PostUpdateRequest;
import gsm.gistory.domain.post.dto.response.PostUpdateResponse;
import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.post.service.PostUpdateService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostUpdateServiceImpl implements PostUpdateService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public PostUpdateResponse updatePost(PostUpdateRequest request, String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        String token = authorizationHeader.substring(7);
        jwtTokenProvider.validateToken(token);

        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REQUEST, "게시글이 존재하지 않습니다."));

        String email = jwtTokenProvider.getEmailFromToken(token);

        if (!post.getEmail().equals(email)) {
            throw new CustomException(ErrorCode.INVALID_REQUEST, "수정 권한이 없습니다.");
        }

        post.updateTitleAndContent(request.getTitle(), request.getContent());

        return PostUpdateResponse.builder()
                .message("게시글 수정 성공")
                .statusCode(200)
                .build();
    }
}
