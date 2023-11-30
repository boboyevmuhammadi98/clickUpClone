package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.TaskAttachmentDto;
import uz.alif.click_up_clone.dtos.TaskDto;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.enums.StatusType;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.ChecklistService;
import uz.alif.click_up_clone.service.serviceInterface.TaskService;

import java.util.UUID;

@Service
public class CheckListServiceImpl implements ChecklistService {

    private final TaskRepository taskRepository;
    private final CheckListRepository checkListRepository;
    private final CheckListItemRepository checkListItemRepository;
    private final UserRepository userRepository;

    public CheckListServiceImpl(TaskRepository taskRepository,
                                CheckListRepository checkListRepository,
                                CheckListItemRepository checkListItemRepository,
                                UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.checkListRepository = checkListRepository;
        this.checkListItemRepository = checkListItemRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse add(UUID taskId, String name) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("task"));
        CheckList save = checkListRepository.save(new CheckList(name, task));
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse edit(UUID checklistId, String name) {
        CheckList checklist = checkListRepository.findById(checklistId).orElseThrow(() -> new ResourceNotFoundException("checklist"));
        checklist.setName(name);
        CheckList save = checkListRepository.save(checklist);
        return new ApiResponse("edited", true, 201, save);
    }

    @Override
    public ApiResponse delete(UUID checklistId) {
        try {
            checkListRepository.deleteById(checklistId);
            return new ApiResponse("deleted", true, 404);
        } catch (Exception e) {
            return new ApiResponse("can not delete", false, 404);
        }
    }


    @Override
    public ApiResponse addCheckListItem(UUID checkListId, String name) {
        CheckList checklist = checkListRepository.findById(checkListId).orElseThrow(() -> new ResourceNotFoundException("checklist"));
        CheckListItem save = checkListItemRepository.save(new CheckListItem(name, checklist, false, null));
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse deleteItem(UUID checkListItemId) {
        try {
            checkListItemRepository.deleteById(checkListItemId);
            return new ApiResponse("deleted", true, 404);
        } catch (Exception e) {
            return new ApiResponse("can not delete", false, 404);
        }
    }


    @Override
    public ApiResponse assignUserToItem(UUID checkListItemId, UUID userId) {
        CheckListItem checklistItem = checkListItemRepository.findById(checkListItemId).orElseThrow(() -> new ResourceNotFoundException("checklist item"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user"));
        checklistItem.setAssignedUser(user);
        CheckListItem save = checkListItemRepository.save(checklistItem);
        return new ApiResponse("assigned", true, 201, save);
    }
}