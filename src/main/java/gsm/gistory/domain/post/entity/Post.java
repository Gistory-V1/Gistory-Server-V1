package gsm.gistory.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long likeCount;
    private String author;
    private Long authorId;
    private Long views;

    private LocalDateTime createdAt;

    public void incrementViews() {
        this.views = this.views + 1;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
