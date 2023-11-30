package uz.alif.click_up_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.AccessType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class View extends AbstractUUIDEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne( fetch = FetchType.LAZY)
    private Icon icon;

}
