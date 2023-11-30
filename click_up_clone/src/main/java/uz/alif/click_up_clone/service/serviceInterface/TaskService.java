package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.TaskAttachmentDto;
import uz.alif.click_up_clone.dtos.TaskDto;

import java.util.UUID;

public interface TaskService {

    ApiResponse add(TaskDto taskDto);

    ApiResponse editStatus(UUID taskId, UUID statusId);

    ApiResponse addAttachment(TaskAttachmentDto taskAttachmentDto);

    ApiResponse deleteAttachment(UUID id);

    ApiResponse addComment(UUID id, String name);

    ApiResponse editComment(UUID id, String name);

    ApiResponse deleteComment(UUID id);

    ApiResponse assignUser(UUID taskId, UUID userId);

    ApiResponse deleteTaskUser(UUID taskId, UUID userId);
}
