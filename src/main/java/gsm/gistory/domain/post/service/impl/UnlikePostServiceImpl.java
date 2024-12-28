package gsm.gistory.domain.post.service.impl;

import gsm.gistory.domain.post.dto.response.LikePostResponse;
import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.post.service.UnlikePostService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UnlikePostServiceImpl implements UnlikePostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public LikePostResponse unlikePost(Long postId, String authorization) {
        if (authorization == null || !authorization.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        String token = authorization.substring(7);
        jwtTokenProvider.validateToken(token);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REQUEST, "게시글이 존재하지 않습니다."));

        post.decrementLikeCount();

        postRepository.save(post);

        return LikePostResponse.builder()
                .message("좋아요 취소 성공")
                .likeCount(post.getLikeCount())
                .statusCode(200)
                .build();
    }
}
