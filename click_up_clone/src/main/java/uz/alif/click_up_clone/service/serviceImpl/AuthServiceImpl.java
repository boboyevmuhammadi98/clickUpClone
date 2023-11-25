package uz.alif.click_up_clone.service.serviceImpl;

import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ServerErrorException;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.ApiResponseWithToken;
import uz.alif.click_up_clone.dtos.RegisterUserDto;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.enums.SystemRoleName;
import uz.alif.click_up_clone.repository.UserRepository;
import uz.alif.click_up_clone.security.JwtService;
import uz.alif.click_up_clone.service.serviceInterface.AuthService;
import uz.alif.click_up_clone.utils.Utils;

import javax.security.auth.login.LoginException;

@Service
public class AuthServiceImpl implements AuthService {

    @Lazy
    @Autowired
    PasswordEncoder passwordEncoder;

    @Lazy
    @Autowired
    JwtService jwtService;

    @Lazy
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Lazy
    @Autowired
    JavaMailSender javaMailSender;


    public ApiResponse register(RegisterUserDto registerUserDto) {
        if (userRepository.existsByEmail(registerUserDto.getEmail()))
            return new ApiResponse("email number already exists", false, 409, null);

        String code = Utils.codeGenerator(4);
        sendEmail(registerUserDto.getEmail(), code);
        User user = new User(
                registerUserDto.getFullName(),
                registerUserDto.getEmail(),
                passwordEncoder.encode(registerUserDto.getPassword()),
                registerUserDto.getColor(),
                registerUserDto.getFullName().substring(0, 1),
                code,
                SystemRoleName.SYSTEM_USER
        );
        User save = userRepository.save(user);
        return new ApiResponse("created", true, 201, save);
    }


    public ApiResponseWithToken verifyUser(String email, String code) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found"));
        if (user.getVerificationCode().equals(code)) {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return new ApiResponseWithToken("verified", true, 200, jwtService.generateToken(user.getEmail()));
        }
        return new ApiResponseWithToken("verification code is incorrect", false, 400, null);
    }

    public ApiResponseWithToken login(String email, String password) {
        try {
            User principal = (User) authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password)).getPrincipal();
            return new ApiResponseWithToken("success", true, 200, jwtService.generateToken(principal.getEmail()));
        } catch (Exception e) {
            return new ApiResponseWithToken(e.getMessage(), false, 400, null);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("user not found"));
    }

    @Value("${spring.mail.username}")
    private String springMailUsername;

    public boolean sendEmail(String sendingEmail, String message) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(message, true);
            helper.setTo(sendingEmail);
            helper.setSubject("email verification by muslimdev");
            helper.setFrom(springMailUsername);
            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            throw new ServerErrorException("do not send message the given email", e.getCause());
        }
    }
}
