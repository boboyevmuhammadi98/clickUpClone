package uz.alif.click_up_clone.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import uz.alif.click_up_clone.entity.TaskUser;

import java.util.UUID;

public interface TaskUserRepository extends JpaRepository<TaskUser, UUID> {
    @Transactional
    @Modifying
    void deleteByTaskIdAndUserId(UUID task_id, UUID user_id);
}