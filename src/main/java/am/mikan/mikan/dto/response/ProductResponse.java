package am.mikan.mikan.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponse {

    private String title;
    private String description;
    private String image;
}
