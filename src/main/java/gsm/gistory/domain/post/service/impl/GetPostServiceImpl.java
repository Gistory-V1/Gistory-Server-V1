package gsm.gistory.domain.post.service.impl;

import gsm.gistory.domain.post.dto.response.GetPostResponse;
import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.post.service.GetPostService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class GetPostServiceImpl implements GetPostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public GetPostResponse getPost(Long postId, String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        String token = authorizationHeader.substring(7);
        jwtTokenProvider.validateToken(token);

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_REQUEST, "게시글이 존재하지 않습니다."));

        post.incrementViews();
        postRepository.save(post);

        return GetPostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .author(post.getEmail())
                .views(post.getViews())
                .createdAt(post.getCreatedAt().toString())
                .build();
    }
}
