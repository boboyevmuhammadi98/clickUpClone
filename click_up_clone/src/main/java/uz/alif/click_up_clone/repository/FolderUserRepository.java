package uz.alif.click_up_clone.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import uz.alif.click_up_clone.entity.FolderUser;

import java.util.UUID;

public interface FolderUserRepository extends JpaRepository<FolderUser, UUID> {
    @Transactional
    @Modifying
    void deleteByFolderIdAndUserId(UUID folder_id, UUID user_id);
}