package uz.alif.click_up_clone.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import uz.alif.click_up_clone.entity.Attachment;

import java.util.UUID;

@Data
public class WorkspaceDto {
    @NotBlank(message = "workspace name can not be blank")
    private String name;
    @NotBlank(message = "workspace name can not be blank")
    private String color;

    private UUID attachmentId;

}
