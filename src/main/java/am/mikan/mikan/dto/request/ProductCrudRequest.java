package am.mikan.mikan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCrudRequest {

    @NotBlank
    private String titleUs;
    @NotBlank
    private String titleRu;
    @NotBlank
    private String titleHy;
    @NotBlank
    @Size(min = 8, max = 500)
    private String descriptionUs;
    @Size(min = 8, max = 500)
    private String descriptionRu;
    @Size(min = 8, max = 500)
    private String descriptionHy;
    private String image;
}
