package uz.alif.click_up_clone.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import uz.alif.click_up_clone.entity.WorkspaceUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspaceUserRepository extends JpaRepository<WorkspaceUser, UUID> {

    Optional<WorkspaceUser> findByWorkspaceIdAndUserId(Long workspace_id, UUID user_id);

    @Transactional
    @Modifying
    void deleteByWorkspaceIdAndUserId(Long workspace_id, UUID user_id);

    List<WorkspaceUser> findAllByWorkspaceId(Long workspace_id);

    List<WorkspaceUser> findAllByUserId(UUID user_id);

}