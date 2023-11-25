package uz.alif.click_up_clone.entity.template;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import uz.alif.click_up_clone.entity.User;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@MappedSuperclass
public class AbstractLongEntity extends AbstractMainEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
