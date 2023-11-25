package uz.alif.click_up_clone.service.serviceInterface;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.ApiResponseWithToken;
import uz.alif.click_up_clone.dtos.RegisterUserDto;

import javax.security.auth.login.LoginException;

public interface AuthService extends UserDetailsService {
    ApiResponse register(RegisterUserDto registerUserDto);

    ApiResponseWithToken verifyUser(String email, String code);

    ApiResponseWithToken login(String email, String password) throws LoginException;

    boolean sendEmail(String sendingEmail, String message);

}
