package simple.crm.backend.dto.contact;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ContactResponseDto implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String email;
    private Long clientId;
}
