package simple.crm.backend.dto.client;

import jakarta.validation.constraints.NotEmpty;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientRequestDto implements Serializable {
    @NotEmpty
    private String company;
    @NotEmpty
    private String area;
    @NotEmpty
    private String address;
}
