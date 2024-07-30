package am.mikan.mikan.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductCrudResponse {

    private int id;
    private String titleUs;
    private String titleRu;
    private String titleHy;
    private String descriptionUs;
    private String descriptionRu;
    private String descriptionHy;
    private String image;
}
