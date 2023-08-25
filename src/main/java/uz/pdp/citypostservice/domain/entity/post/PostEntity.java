package uz.pdp.citypostservice.domain.entity.post;

import jakarta.persistence.Entity;
import lombok.*;
import uz.pdp.citypostservice.domain.entity.BaseEntity;

import java.util.UUID;

@Entity(name = "posts")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PostEntity extends BaseEntity {
    private String name;
    private String description;
    private UUID ownerId;
    private Double price;
    private String telephoneNumber;
    private PostStatus status;
}
