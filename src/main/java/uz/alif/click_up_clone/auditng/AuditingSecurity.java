package uz.alif.click_up_clone.auditng;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import uz.alif.click_up_clone.entity.User;

import java.util.Optional;

public class AuditingSecurity implements AuditorAware<User> {
    @Override
    public Optional<User> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && !authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.of(((User) authentication.getPrincipal()));
        }
        return Optional.empty();
    }
}
