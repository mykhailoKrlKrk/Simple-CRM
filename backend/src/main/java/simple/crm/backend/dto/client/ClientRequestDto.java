package simple.crm.backend.dto.client;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientRequestDto {
    @NotEmpty
    private String company;
    @NotEmpty
    private String area;
    @NotEmpty
    private String address;
}
