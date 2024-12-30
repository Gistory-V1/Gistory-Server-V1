package gsm.gistory.domain.rank.service;

import gsm.gistory.domain.post.entity.Post;
import gsm.gistory.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Map<String, Object>> getTop5PostsByViews() {
        Pageable top5 = PageRequest.of(0, 5, Sort.by(Sort.Direction.DESC, "views"));
        List<Post> posts = postRepository.findTop5ByViews(top5);

        List<Map<String, Object>> result = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (int i = 0; i < posts.size(); i++) {
            Post post = posts.get(i);
            Map<String, Object> postMap = new HashMap<>();
            postMap.put("rank", i + 1);
            postMap.put("postId", post.getId());
            postMap.put("name", post.getName());
            postMap.put("title", post.getTitle());
            postMap.put("likeCount", post.getLikeCount());
            postMap.put("views", post.getViews());
            postMap.put("createdAt", post.getCreatedAt().format(formatter));
            result.add(postMap);
        }

        return result;
    }

}

