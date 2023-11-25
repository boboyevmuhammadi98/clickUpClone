package uz.alif.click_up_clone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.AccessType;
import uz.alif.click_up_clone.enums.TaskPermission;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class FolderUser extends AbstractUUIDEntity {

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Folder folder;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @Enumerated(EnumType.STRING)
    private TaskPermission taskPermission;

}
