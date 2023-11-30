package uz.alif.click_up_clone.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.StatusDto;
import uz.alif.click_up_clone.dtos.TaskAttachmentDto;
import uz.alif.click_up_clone.dtos.TaskDto;
import uz.alif.click_up_clone.service.serviceInterface.StatusService;
import uz.alif.click_up_clone.service.serviceInterface.TaskService;

import java.util.UUID;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    TaskService taskService;

    @PostMapping("/{id}")
    public ResponseEntity<?> add(@PathVariable UUID id, @Valid @RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.add(taskDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/editStatus/{id}")
    public ResponseEntity<?> editStatus(@PathVariable UUID id, @Valid @RequestParam UUID statusId) {
        ApiResponse apiResponse = taskService.editStatus(id, statusId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/addAttachment")
    public ResponseEntity<?> addAttachment(@RequestBody TaskAttachmentDto taskAttachmentDto) {
        ApiResponse apiResponse = taskService.addAttachment(taskAttachmentDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("/deleteAttachment/{id}")
    public ResponseEntity<?> addAttachment(@PathVariable UUID id) {
        ApiResponse apiResponse = taskService.deleteAttachment(id);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PostMapping("addComment/{id}")
    public ResponseEntity<?> addComment(@PathVariable UUID id, @RequestParam String name) {
        ApiResponse apiResponse = taskService.addComment(id, name);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("editComment/{id}")
    public ResponseEntity<?> editComment(@PathVariable UUID id, @RequestParam String name) {
        ApiResponse apiResponse = taskService.editComment(id, name);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("deleteComment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable UUID id) {
        ApiResponse apiResponse = taskService.deleteComment(id);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PostMapping("assignUser/{taskId}")
    public ResponseEntity<?> assignUser(@PathVariable UUID taskId, @RequestParam UUID userId) {
        ApiResponse apiResponse = taskService.assignUser(taskId, userId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("deleteTaskUser")
    public ResponseEntity<?> deleteTaskUser(@RequestParam UUID taskId, @RequestParam UUID userId) {
        ApiResponse apiResponse = taskService.deleteTaskUser(taskId, userId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

}
