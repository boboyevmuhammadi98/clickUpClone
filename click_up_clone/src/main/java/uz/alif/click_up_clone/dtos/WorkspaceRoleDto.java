package uz.alif.click_up_clone.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import uz.alif.click_up_clone.entity.WorkspacePermission;
import uz.alif.click_up_clone.enums.WorkspacePermissionName;
import uz.alif.click_up_clone.enums.WorkspaceRoleName;

import java.util.Set;

@Data
public class WorkspaceRoleDto {

    @NotBlank
    private String name;

    @NotBlank
    private Long workspaceId;

    @NotNull
    private WorkspaceRoleName extendsRole;

    @NotEmpty
    private Set<WorkspacePermissionName> workspacePermissionNames;

}
