package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.StatusDto;
import uz.alif.click_up_clone.dtos.TaskAttachmentDto;
import uz.alif.click_up_clone.dtos.TaskDto;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.enums.StatusType;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.StatusService;
import uz.alif.click_up_clone.service.serviceInterface.TaskService;

import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {

    private final StatusRepository statusRepository;
    private final TaskRepository taskRepository;
    private final FolderRepository folderRepository;
    private final AttachmentRepository attachmentRepository;
    private final Task_AttachmentRepository task_AttachmentRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final TaskUserRepository taskUserRepository;

    public TaskServiceImpl(StatusRepository statusRepository,
                           TaskRepository taskRepository,
                           FolderRepository folderRepository,
                           AttachmentRepository attachmentRepository,
                           Task_AttachmentRepository task_AttachmentRepository,
                           CommentRepository commentRepository,
                           UserRepository userRepository,
                           TaskUserRepository taskUserRepository) {
        this.statusRepository = statusRepository;
        this.taskRepository = taskRepository;
        this.folderRepository = folderRepository;
        this.attachmentRepository = attachmentRepository;
        this.task_AttachmentRepository = task_AttachmentRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.taskUserRepository = taskUserRepository;
    }

    @Override
    public ApiResponse add(TaskDto taskDto) {
        Task save = taskRepository.save(new Task(
                taskDto.getName(),
                taskDto.getDescription(),
                statusRepository.findByStatusType(StatusType.OPEN).orElseThrow(() -> new ResourceNotFoundException("status")),
                folderRepository.findById(taskDto.getFolderId()).orElseThrow(() -> new ResourceNotFoundException("folder")),
                taskDto.getParentTaskId() == null ? null : taskRepository.findById(taskDto.getParentTaskId()).orElseThrow(() -> new ResourceNotFoundException("task"))
        ));
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse editStatus(UUID taskId, UUID statusId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("task"));
        Status status = statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("status"));
        task.setStatus(status);
        Task save = taskRepository.save(task);
        return new ApiResponse("status edited", true, 201, save);
    }

    @Override
    public ApiResponse addAttachment(TaskAttachmentDto taskAttachmentDto) {
        Attachment attachment = attachmentRepository.findById(taskAttachmentDto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("attachment"));
        Task task = taskRepository.findById(taskAttachmentDto.getTaskId()).orElseThrow(() -> new ResourceNotFoundException("task"));
        Task_Attachment save = task_AttachmentRepository.save(new Task_Attachment(task, attachment, taskAttachmentDto.isPinCoverImage()));
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse deleteAttachment(UUID id) {
        try {
            task_AttachmentRepository.deleteById(id);
            return new ApiResponse("deleted", true, 404);
        } catch (Exception e) {
            return new ApiResponse("can not delete", false, 404);
        }
    }

    @Override
    public ApiResponse addComment(UUID id, String name) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("task"));
        Comment save = commentRepository.save(new Comment(
                name,
                task
        ));
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse editComment(UUID id, String name) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("comment"));
        comment.setName(name);
        Comment save = commentRepository.save(comment);
        return new ApiResponse("edited", true, 201, save);
    }

    @Override
    public ApiResponse deleteComment(UUID id) {
        try {
            commentRepository.deleteById(id);
            return new ApiResponse("deleted", true, 404);
        } catch (Exception e) {
            return new ApiResponse("can not delete", false, 404);
        }
    }

    @Override
    public ApiResponse assignUser(UUID taskId, UUID userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new ResourceNotFoundException("task"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user"));
        TaskUser save = taskUserRepository.save(new TaskUser(task, user));
        return new ApiResponse("assigned", true, 201, save);
    }

    @Override
    public ApiResponse deleteTaskUser(UUID taskId, UUID userId) {
        try {
            taskUserRepository.deleteByTaskIdAndUserId(taskId, userId);
            return new ApiResponse("deleted", true, 404);
        } catch (Exception e) {
            return new ApiResponse("can not delete", false, 404);
        }
    }
}