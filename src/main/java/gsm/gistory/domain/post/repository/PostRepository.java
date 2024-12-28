package gsm.gistory.domain.post.repository;

import gsm.gistory.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Query("SELECT p FROM Post p ORDER BY p.views DESC")
    List<Post> findTop5ByViews();
}
