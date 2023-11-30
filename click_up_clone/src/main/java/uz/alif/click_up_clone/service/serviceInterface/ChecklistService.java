package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.TaskAttachmentDto;
import uz.alif.click_up_clone.dtos.TaskDto;

import java.util.UUID;

public interface ChecklistService {

    ApiResponse add(UUID taskId, String name);

    ApiResponse edit(UUID checklistId, String name);

    ApiResponse delete(UUID checklistId);

    ApiResponse addCheckListItem(UUID checkListId, String name);

    ApiResponse deleteItem(UUID checkListItemId);

    ApiResponse assignUserToItem(UUID checkListItemId, UUID userId);
}
