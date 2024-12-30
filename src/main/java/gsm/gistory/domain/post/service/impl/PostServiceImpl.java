package gsm.gistory.domain.post.service.impl;

import gsm.gistory.domain.post.dto.response.GetPostsResponse;
import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import gsm.gistory.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Override
    public List<GetPostsResponse> getPostsByAuthorName(String name) {
        List<Post> posts = postRepository.findByName(name);

        return posts.stream().map(post -> GetPostsResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .likeCount(post.getLikeCount())
                .views(post.getViews())
                .build()
        ).collect(Collectors.toList());
    }
}
