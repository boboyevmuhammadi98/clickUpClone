package uz.alif.click_up_clone.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.alif.click_up_clone.entity.template.AbstractUUIDEntity;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Task extends AbstractUUIDEntity {

    @Column(nullable = false)
    private String name;

    @Column()
    private String description;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Status status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Folder folder;

    @ManyToOne()
    private Task parentTask;

    @Column()
    private Timestamp startedDate;

    @Column()
    private boolean startTimeHas;

    @Column()
    private Timestamp dueDate;

    @Column()
    private boolean dueTimeHas;

    @Column()
    private Long estimateTime;

    @Column()
    private Timestamp activeDate;

    public Task(String name, String description, Status status, Folder folder, Task parentTask) {
        this.name = name;
        this.description = description;
        this.status = status;
        this.folder = folder;
        this.parentTask = parentTask;
    }
}
