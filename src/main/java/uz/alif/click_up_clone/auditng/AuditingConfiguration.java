package uz.alif.click_up_clone.auditng;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import uz.alif.click_up_clone.entity.User;

@Configuration
@EnableJpaAuditing
public class AuditingConfiguration {
    @Bean
    public AuditorAware<User> uuidAuditorProvider() {
        return new AuditingSecurity();
    }

}
