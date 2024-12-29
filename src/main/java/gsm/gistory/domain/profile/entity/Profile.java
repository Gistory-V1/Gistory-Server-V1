package gsm.gistory.domain.profile.entity;

import gsm.gistory.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "profile")
    private List<Post> posts;

    @Column(nullable = false, unique = true)
    private String email;

    private Long subCount;
}
