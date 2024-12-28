package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.response.GetPostResponse;

public interface GetPostService {
    GetPostResponse getPost(Long postId, String authorizationHeader);
}
