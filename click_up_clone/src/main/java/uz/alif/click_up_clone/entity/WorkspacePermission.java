package uz.alif.click_up_clone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.WorkspacePermissionName;
import uz.alif.click_up_clone.enums.WorkspaceRoleName;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspacePermission extends AbstractUUIDEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WorkspaceRole workspaceRole;

    @Enumerated(EnumType.STRING)
    private WorkspacePermissionName workspacePermissionName;
}
