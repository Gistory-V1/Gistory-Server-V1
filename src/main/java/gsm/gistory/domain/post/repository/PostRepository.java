package gsm.gistory.domain.post.repository;

import gsm.gistory.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {

    Long countByAuthorId(Long authorId);

    @Query("SELECT SUM(p.likeCount) FROM Post p WHERE p.authorId = :authorId")
    Long sumLikesByAuthorId(Long authorId);
}
