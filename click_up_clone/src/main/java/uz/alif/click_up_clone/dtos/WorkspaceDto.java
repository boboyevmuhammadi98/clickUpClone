package uz.alif.click_up_clone.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import uz.alif.click_up_clone.entity.Attachment;

import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WorkspaceDto {
    @NotBlank(message = "workspace name can not be blank")
    private String name;
    @NotBlank(message = "workspace name can not be blank")
    private String color;

    private UUID attachmentId;

    private String initialLetter;

    private Long id;

    public WorkspaceDto(String name, String color, UUID attachmentId) {
        this.name = name;
        this.color = color;
        this.attachmentId = attachmentId;
    }

    public WorkspaceDto(String name, String color, UUID attachmentId, String initialLetter, Long id) {
        this.name = name;
        this.color = color;
        this.attachmentId = attachmentId;
        this.initialLetter = initialLetter;
        this.id = id;
    }
}
