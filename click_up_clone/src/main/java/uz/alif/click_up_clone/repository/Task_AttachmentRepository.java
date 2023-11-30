package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.Task_Attachment;

import java.util.UUID;

public interface Task_AttachmentRepository extends JpaRepository<Task_Attachment, UUID> {
}