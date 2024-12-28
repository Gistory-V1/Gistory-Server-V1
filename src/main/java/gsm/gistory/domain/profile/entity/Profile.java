package gsm.gistory.domain.profile.entity;

import gsm.gistory.domain.post.entity.Post;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Getter
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "profile")
    private List<Post> posts;

    private Long subCount;
}
