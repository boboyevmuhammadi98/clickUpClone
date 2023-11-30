package uz.alif.click_up_clone.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.alif.click_up_clone.entity.Attachment;
import uz.alif.click_up_clone.entity.Task;

import java.util.UUID;

@Data
public class TaskAttachmentDto {
    private UUID taskId;

    private UUID attachmentId;

    private boolean pinCoverImage;
}
