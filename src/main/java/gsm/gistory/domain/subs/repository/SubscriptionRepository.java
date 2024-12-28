package gsm.gistory.domain.subs.repository;

import gsm.gistory.domain.subs.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByName(String name);
}
