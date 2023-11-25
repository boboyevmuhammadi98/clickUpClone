package uz.alif.click_up_clone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.AccessType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Folder extends AbstractUUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Space space;

    @Column(nullable = false)
    private AccessType accessType;

    @Column(nullable = false)
    private boolean archived;

}
