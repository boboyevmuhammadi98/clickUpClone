package uz.alif.click_up_clone.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import uz.alif.click_up_clone.enums.UserAddType;

import java.util.UUID;

@Data
@AllArgsConstructor
public class WorkspaceUserDtoResponse {
    private UUID id;
    private String fullName;
    private String email;
    private String roleName;
}
