package gsm.gistory.domain.post.service.impl;

import gsm.gistory.domain.auth.util.StudentEmailMapper;
import gsm.gistory.domain.post.dto.request.CreatePostRequest;
import gsm.gistory.domain.post.dto.response.CreatePostResponse;
import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.post.service.CreatePostService;
import gsm.gistory.global.security.jwt.JwtTokenProvider;
import gsm.gistory.global.security.jwt.exception.CustomException;
import gsm.gistory.global.security.jwt.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostServiceImpl implements CreatePostService {

    private final PostRepository postRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public CreatePostResponse createPost(CreatePostRequest request, String authorizationHeader) {
        validatePostRequest(request);

        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 인증 헤더입니다.");
        }

        String token = authorizationHeader.substring(7);
        if (!jwtTokenProvider.validateToken(token)) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "유효하지 않은 토큰입니다.");
        }

        String email = jwtTokenProvider.getEmailFromToken(token);
        if (email == null || email.isBlank()) {
            throw new CustomException(ErrorCode.INVALID_TOKEN, "이메일 정보를 확인할 수 없습니다.");
        }

        String name = StudentEmailMapper.getNameByEmail(email);
        if (name == null || name.equals("Unknown")) {
            throw new CustomException(ErrorCode.INVALID_REQUEST, "작성자의 이름을 확인할 수 없습니다.");
        }

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .email(email)
                .name(name)
                .build();

        postRepository.save(post);

        return CreatePostResponse.builder()
                .message("글 작성 성공")
                .statusCode(201)
                .build();
    }

    private void validatePostRequest(CreatePostRequest request) {
        if (request.getTitle() == null || request.getTitle().length() < 1 || request.getTitle().length() > 15) {
            throw new CustomException(ErrorCode.INVALID_REQUEST, "제목은 1 ~ 15글자여야 합니다.");
        }
        if (request.getContent() == null || request.getContent().length() < 1 || request.getContent().length() > 500) {
            throw new CustomException(ErrorCode.INVALID_REQUEST, "내용은 1 ~ 500자여야 합니다.");
        }
    }
}
