package uz.alif.click_up_clone.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.aop.CurrentUser;
import uz.alif.click_up_clone.dtos.*;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.service.serviceInterface.SpaceService;
import uz.alif.click_up_clone.service.serviceInterface.WorkspaceService;

import java.util.UUID;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    SpaceService spaceService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getSpaceByWorkspaceId(@PathVariable Long id) {
        return ResponseEntity.ok(spaceService.getSpaceByWorkspaceId(id));
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> add(@PathVariable Long id, @Valid @RequestBody SpaceDto spaceDto, @CurrentUser User owner) {
        ApiResponse apiResponse = spaceService.add(id, spaceDto, owner);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable UUID id, @Valid @RequestBody SpaceDto spaceDto) {
        ApiResponse apiResponse = spaceService.edit(id, spaceDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        ApiResponse apiResponse = spaceService.delete(id);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }
}
