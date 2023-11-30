package uz.alif.click_up_clone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.service.serviceInterface.ChecklistService;

import java.util.UUID;

@RestController
@RequestMapping("/api/checklist")
public class ChecklistController {

    @Autowired
    ChecklistService checkListService;

    @PostMapping("/{taskId}")
    public ResponseEntity<?> add(@PathVariable UUID taskId, @RequestParam String name) {
        ApiResponse apiResponse = checkListService.add(taskId, name);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/{checkListId}")
    public ResponseEntity<?> editStatus(@PathVariable UUID checkListId, @RequestParam String name) {
        ApiResponse apiResponse = checkListService.edit(checkListId, name);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("/{checkListId}")
    public ResponseEntity<?> addAttachment(@PathVariable UUID checkListId) {
        ApiResponse apiResponse = checkListService.delete(checkListId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }
    @PostMapping("addItem/{checkListId}")
    public ResponseEntity<?> addCheckListItem(@PathVariable UUID checkListId, @RequestParam String name) {
        ApiResponse apiResponse = checkListService.addCheckListItem(checkListId, name);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("deleteItem/{checkListItemId}")
    public ResponseEntity<?> deleteItem(@PathVariable UUID checkListItemId) {
        ApiResponse apiResponse = checkListService.deleteItem(checkListItemId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("assignUserToItem/{checkListItemId}")
    public ResponseEntity<?> assignUserToItem(@PathVariable UUID checkListItemId, @RequestParam UUID userId) {
        ApiResponse apiResponse = checkListService.assignUserToItem(checkListItemId, userId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }




}
