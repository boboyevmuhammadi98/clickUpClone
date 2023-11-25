package uz.alif.click_up_clone.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class WorkspaceUser extends AbstractUUIDEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Workspace workspace;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private WorkspaceRole workspaceRole;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp dateInvited;

    @Column()
    private Timestamp dateJoined;
}
