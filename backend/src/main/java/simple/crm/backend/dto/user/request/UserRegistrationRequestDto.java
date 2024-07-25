package simple.crm.backend.dto.user.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import simple.crm.backend.validation.FieldMatch;
import simple.crm.backend.validation.Mail;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@FieldMatch(
        field = "password",
        fieldMatch = "repeatPassword",
        message = "Passwords do not match!"
)
public class UserRegistrationRequestDto {
    @NotBlank
    @Size(min = 4, max = 50)
    @Mail
    private String email;
    @NotBlank
    @Size(min = 4, max = 100)
    private String password;
    private String repeatPassword;
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    @NotBlank
    @Size(min = 2, max = 30)
    private String lastName;

}
