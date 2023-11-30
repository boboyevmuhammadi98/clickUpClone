package uz.alif.click_up_clone.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.alif.click_up_clone.entity.Folder;
import uz.alif.click_up_clone.entity.Status;
import uz.alif.click_up_clone.entity.Task;
import uz.alif.click_up_clone.enums.StatusType;

import java.sql.Timestamp;
import java.util.UUID;

@Data
public class TaskDto {
    private String name;

    private String description;

    private UUID folderId;

    private UUID parentTaskId;
}
