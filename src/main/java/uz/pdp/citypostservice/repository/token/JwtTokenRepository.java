package uz.pdp.citypostservice.repository.token;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.citypostservice.domain.entity.response.JwtTokenEntity;

@Repository
public interface JwtTokenRepository extends JpaRepository<JwtTokenEntity,String> {
}
