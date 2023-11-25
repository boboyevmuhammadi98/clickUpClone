package uz.alif.authentication_news_app.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.entity.WorkspaceRole;
import uz.alif.click_up_clone.enums.SystemRoleName;
import uz.alif.click_up_clone.repository.UserRepository;
import uz.alif.click_up_clone.repository.WorkspaceRoleRepository;

import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Value(value = "${spring.sql.init.mode}")
    private String sqlInitMode;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkspaceRoleRepository workspaceRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        if (sqlInitMode.equals("always")) {
            userRepository.save(
                    new User(
                            "admin",
                            "admin@gmail.com",
                            passwordEncoder.encode("admin"),
                            "green",
                            null,
                            SystemRoleName.SYSTEM_ADMIN

                    )
            );
        }
    }
}
