package uz.alif.click_up_clone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractLongEntity;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;
import uz.alif.click_up_clone.enums.AccessType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Space extends AbstractUUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @Column(nullable = false)
    private String initialLetter;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Workspace workspace;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User owner;

    @OneToOne(fetch = FetchType.LAZY)
    private Icon icon;

    @Column(nullable = false)
    private AccessType accessType;

    @PrePersist
    @PreUpdate
    public void initialLetterInitiator() {
        this.setInitialLetter(getName().substring(0, 1));
    }

    public Space(String name, String color, Workspace workspace, User owner, Icon icon, AccessType accessType) {
        this.name = name;
        this.color = color;
        this.workspace = workspace;
        this.owner = owner;
        this.icon = icon;
        this.accessType = accessType;
    }
}
