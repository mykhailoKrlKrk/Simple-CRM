package simple.crm.backend.dto.client;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ClientResponseDto {
    private Long id;
    private String company;
    private String area;
    private String address;
}
