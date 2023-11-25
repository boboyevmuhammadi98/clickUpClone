package uz.alif.click_up_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Icon extends AbstractUUIDEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false, updatable = false)
    private String initialLetter;

    @OneToOne(optional = false)
    private Attachment attachment;
}
