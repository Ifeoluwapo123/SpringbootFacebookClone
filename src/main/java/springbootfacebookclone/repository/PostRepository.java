package springbootfacebookclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootfacebookclone.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
