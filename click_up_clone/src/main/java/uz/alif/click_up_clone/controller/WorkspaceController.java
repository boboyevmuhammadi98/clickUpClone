package uz.alif.click_up_clone.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.aop.CurrentUser;
import uz.alif.click_up_clone.dtos.*;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.entity.WorkspaceUser;
import uz.alif.click_up_clone.service.serviceInterface.WorkspaceService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/workspace")
public class WorkspaceController {

    @Autowired
    WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<?> getWorkspacesByUser(@CurrentUser User user) {
        List<WorkspaceDto> workspaceDtos = workspaceService.getWorkspacesByUser(user);
        return ResponseEntity.ok(workspaceDtos);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getWorkspaceMember(@PathVariable Long id) {
        List<WorkspaceUserDtoResponse> workspaceUsers = workspaceService.getWorkspaceMember(id);
        return ResponseEntity.ok(workspaceUsers);
    }

    @PostMapping()
    public ResponseEntity<?> add(@Valid @RequestBody WorkspaceDto workspaceDto, @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.add(workspaceDto, user);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @Valid @RequestBody WorkspaceDto workspaceDto) {
        ApiResponse apiResponse = workspaceService.edit(id, workspaceDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("changeOwner/{id}")
    public ResponseEntity<?> changeOwner(@PathVariable Long id, @RequestParam UUID userId) {
        ApiResponse apiResponse = workspaceService.changeOwner(id, userId);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ApiResponse apiResponse = workspaceService.delete(id);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> addOrEditOrRemoveWorkspaceUser(@PathVariable Long id, @Valid @RequestBody WorkspaceUserDto workspaceUserDto) {
        ApiResponse apiResponse = workspaceService.addOrEditOrRemoveWorkspaceUser(id, workspaceUserDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/joinWorkspace")
    public ResponseEntity<?> joinWorkspace(@RequestParam Long id, @CurrentUser User user) {
        ApiResponse apiResponse = workspaceService.joinWorkspace(id, user);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PostMapping("/role/{id}")
    public ResponseEntity<?> addRole(@PathVariable Long id, @Valid @RequestBody WorkspaceRoleDto workspaceRoleDto) {
        ApiResponse apiResponse = workspaceService.addRoleToWorkspace(id, workspaceRoleDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }


}
