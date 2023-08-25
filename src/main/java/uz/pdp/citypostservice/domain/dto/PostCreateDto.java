package uz.pdp.citypostservice.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class PostCreateDto {
    private String name;
    private String description;
    private Double price;
    private String telephoneNumber;
}
