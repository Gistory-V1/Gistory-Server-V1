package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.request.PostUpdateRequest;
import gsm.gistory.domain.post.dto.response.PostUpdateResponse;

public interface PostUpdateService {
    PostUpdateResponse updatePost(PostUpdateRequest request, String authorizationHeader);
}
