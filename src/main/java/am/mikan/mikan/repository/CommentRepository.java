package am.mikan.mikan.repository;

import am.mikan.mikan.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Feedback, Integer> {
}
