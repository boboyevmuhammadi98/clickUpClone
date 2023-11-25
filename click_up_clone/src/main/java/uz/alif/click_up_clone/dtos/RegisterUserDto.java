package uz.alif.click_up_clone.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import uz.alif.click_up_clone.entity.Attachment;

import java.util.UUID;

@AllArgsConstructor
@Data
public class RegisterUserDto {

    @NotBlank(message = "fullName cannot be empty")
    @Size(min = 6, message = "fullName is can be minimum 3 characters")
    private String fullName;

    @NotBlank(message = "email cannot be empty")
    @Size(min = 6, message = "email is can be minimum 8 characters")
    @Email(message = "email is not valid")
    private String email;

    @NotBlank(message = "password cannot be empty")
    @Size(min = 6, message = "password is can be minimum 6 characters")
    private String password;

    @NotBlank(message = "color cannot be empty")
    private String color;
}
