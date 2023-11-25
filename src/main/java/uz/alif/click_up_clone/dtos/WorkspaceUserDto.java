package uz.alif.click_up_clone.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;
import uz.alif.click_up_clone.enums.WorkspaceUserAddType;

import java.util.UUID;

@Data
public class WorkspaceUserDto {
    @NonNull
    private UUID memberId;
    @NotNull
    private UUID roleId;
    @NotNull
    private WorkspaceUserAddType workspaceUserAddType;
}
