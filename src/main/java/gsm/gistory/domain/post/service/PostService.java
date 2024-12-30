package gsm.gistory.domain.post.service;

import gsm.gistory.domain.post.dto.response.GetPostsResponse;

import java.util.List;

public interface PostService {
    List<GetPostsResponse> getPostsByAuthorName(String name);
}
