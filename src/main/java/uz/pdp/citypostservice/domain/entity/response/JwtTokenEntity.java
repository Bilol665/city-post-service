package uz.pdp.citypostservice.domain.entity.response;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JwtTokenEntity {
    @Id
    private String username;
    private String token;
}
