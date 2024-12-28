package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.response.LikePostResponse;

public interface UnlikePostService {
    LikePostResponse unlikePost(Long postId, String authorization);
}
