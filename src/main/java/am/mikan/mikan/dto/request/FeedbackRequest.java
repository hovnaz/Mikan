package am.mikan.mikan.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedbackRequest {

    @Email
    @Size(min = 10, max = 50)
    private String email;
    @NotBlank
    @Size(min = 4, max = 255)
    private String message;
}
