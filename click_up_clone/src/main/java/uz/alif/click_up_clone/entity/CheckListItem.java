package uz.alif.click_up_clone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CheckListItem extends AbstractUUIDEntity {

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private CheckList checkList;

    private boolean resolved;

    @OneToOne(fetch = FetchType.LAZY)
    private User assignedUser;
}
