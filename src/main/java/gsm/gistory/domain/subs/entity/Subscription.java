package gsm.gistory.domain.subs.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "subscription")
@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long subCount;

    public void incrementSubCount() {
        this.subCount += 1;
    }

    public void decrementSubCount() {
        if (this.subCount > 0) {
            this.subCount -= 1;
        }
    }
}