package gsm.gistory.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private Long likeCount;
    private String author;
    private Long views;

    private LocalDateTime createdAt;

    public void incrementViews() {
        this.views += 1;
    }
}
