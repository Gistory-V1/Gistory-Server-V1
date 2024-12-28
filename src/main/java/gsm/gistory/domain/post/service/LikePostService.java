package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.response.LikePostResponse;

public interface LikePostService {
    LikePostResponse likePost(Long postId, boolean likeClick, String authorization);
}
