package uz.alif.click_up_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class SpaceView extends AbstractUUIDEntity {

    @ManyToOne( fetch = FetchType.LAZY)
    private Space space;

    @ManyToOne( fetch = FetchType.LAZY)
    private View view;
}
