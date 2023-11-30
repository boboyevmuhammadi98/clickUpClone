package uz.alif.click_up_clone.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.aop.CurrentUser;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.FolderDto;
import uz.alif.click_up_clone.dtos.FolderUserDto;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.service.serviceInterface.FolderService;
import uz.alif.click_up_clone.service.serviceInterface.ViewService;

import java.util.UUID;

@RestController
@RequestMapping("/api/view")
public class ViewController {

    @Autowired
    ViewService viewService;

    @GetMapping("/{spaceId}")
    public ResponseEntity<?> getAllViewBySpaceId(@PathVariable UUID spaceId) {
        return ResponseEntity.ok(viewService.getAllViewBySpace(spaceId));
    }
}
