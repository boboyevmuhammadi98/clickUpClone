package uz.alif.click_up_clone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.AccessType;
import uz.alif.click_up_clone.enums.StatusType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Status extends AbstractUUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    private Space space;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StatusType statusType;
}
