package uz.alif.click_up_clone.controller;

import jakarta.mail.Folder;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.aop.CurrentUser;
import uz.alif.click_up_clone.dtos.*;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.service.serviceInterface.FolderService;
import uz.alif.click_up_clone.service.serviceInterface.SpaceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/folder")
public class FolderController {

    @Autowired
    FolderService folderService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getFolderBySpaceId(@PathVariable UUID id) {
        return ResponseEntity.ok(folderService.getFolderBySpaceId(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> add(@PathVariable UUID id, @Valid @RequestBody FolderDto folderDto, @CurrentUser User user) {
        ApiResponse apiResponse = folderService.add(id, folderDto, user);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable UUID id, @Valid @RequestBody FolderDto folderDto) {
        ApiResponse apiResponse = folderService.edit(id, folderDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        ApiResponse apiResponse = folderService.delete(id);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addOrEditOrRemoveFolderUser(@PathVariable UUID id, @Valid @RequestBody FolderUserDto folderUserDto) {
        ApiResponse apiResponse = folderService.addOrEditOrRemoveFolderUser(id, folderUserDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }
}
