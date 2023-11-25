package uz.alif.click_up_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Attachment extends AbstractUUIDEntity {
    @Column(nullable = false, updatable = false)
    private String name;

    @Column(nullable = false, updatable = false)
    private String originalName;

    @Column(nullable = false, updatable = false)
    private Long size;

    @Column(nullable = false, updatable = false)
    private String contentType;
}
