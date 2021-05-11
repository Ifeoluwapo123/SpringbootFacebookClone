package springbootfacebookclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootfacebookclone.model.Likes;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    List<Likes> findAllByPostPostId(Long postId);
    List<Likes> findAllByPostPostIdAndPersonId(Long postId, Long personId);
}
