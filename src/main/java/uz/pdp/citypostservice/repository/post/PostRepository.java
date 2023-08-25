package uz.pdp.citypostservice.repository.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.citypostservice.domain.entity.post.PostEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, UUID> {
    Optional<PostEntity> findPostEntityByOwnerId(UUID ownerId);
}
