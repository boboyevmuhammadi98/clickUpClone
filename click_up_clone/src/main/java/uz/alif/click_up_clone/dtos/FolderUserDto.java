package uz.alif.click_up_clone.dtos;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.alif.click_up_clone.entity.Folder;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.enums.TaskPermission;
import uz.alif.click_up_clone.enums.UserAddType;

import java.util.UUID;

@Data
public class FolderUserDto {

    private UUID folderId;

    private UUID userId;

    private TaskPermission taskPermission;

    private UserAddType userAddType;

}
