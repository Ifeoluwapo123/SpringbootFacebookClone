package springbootfacebookclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootfacebookclone.model.Comment;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostPostId(Long postId);
}
