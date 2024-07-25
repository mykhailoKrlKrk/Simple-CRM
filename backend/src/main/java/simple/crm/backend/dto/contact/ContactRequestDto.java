package simple.crm.backend.dto.contact;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ContactRequestDto implements Serializable {
    @NotEmpty
    private String name;
    @NotEmpty
    private String phone;
    @NotEmpty
    private String email;
    @NotNull
    private Long clientId;
}
