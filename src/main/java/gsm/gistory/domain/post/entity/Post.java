package gsm.gistory.domain.post.entity;

import gsm.gistory.domain.profile.entity.Profile;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "post")
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String email;

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @Builder.Default
    @Column(nullable = false)
    private Long likeCount = 0L;

    @Builder.Default
    @Column(nullable = false)
    private Long views = 0L;

    @Column(name = "author_name")
    private String authorName;

    @Builder.Default
    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public void incrementViews() {
        this.views += 1;
    }

    public void updateTitleAndContent(String title, String content) {
        if (title != null && !title.isBlank()) {
            this.title = title;
        }
        if (content != null && !content.isBlank()) {
            this.content = content;
        }
    }

    public void incrementLikeCount() {
        this.likeCount += 1;
    }

    public void decrementLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount -= 1;
        }
    }
}
