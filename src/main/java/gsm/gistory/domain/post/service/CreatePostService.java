package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.request.CreatePostRequest;
import gsm.gistory.domain.post.dto.response.CreatePostResponse;

public interface CreatePostService {
    CreatePostResponse createPost(CreatePostRequest request, String email);
}
