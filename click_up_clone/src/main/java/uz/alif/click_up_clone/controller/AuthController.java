package uz.alif.click_up_clone.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.ApiResponseWithToken;
import uz.alif.click_up_clone.dtos.RegisterUserDto;
import uz.alif.click_up_clone.service.serviceInterface.AuthService;

import javax.security.auth.login.LoginException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        ApiResponse register = authService.register(registerUserDto);
        return ResponseEntity.status(register.getCode()).body(register);
    }

    @PostMapping("/verifyUser")
    public ResponseEntity<?> verifyUser(@RequestParam() String email, @RequestParam String code) {
        ApiResponseWithToken verifyUser = authService.verifyUser(email, code);
        return ResponseEntity.status(verifyUser.getCode()).body(verifyUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) throws LoginException {
        ApiResponseWithToken login = authService.login(email, password);
        return ResponseEntity.status(login.getCode()).body(login);
    }
}
