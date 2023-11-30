package uz.alif.click_up_clone.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.aop.CurrentUser;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.StatusDto;
import uz.alif.click_up_clone.dtos.FolderUserDto;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.service.serviceInterface.FolderService;
import uz.alif.click_up_clone.service.serviceInterface.StatusService;

import java.util.UUID;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    @Autowired
    StatusService statusService;
    @PostMapping("/{id}")
    public ResponseEntity<?> add(@PathVariable UUID id, @Valid @RequestBody StatusDto saStatusDto) {
        ApiResponse apiResponse = statusService.add(saStatusDto);
        return ResponseEntity.status(apiResponse.getCode()).body(apiResponse);
    }
}
