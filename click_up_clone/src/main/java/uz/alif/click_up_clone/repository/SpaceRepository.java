package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.Space;

import java.util.List;
import java.util.UUID;

public interface SpaceRepository extends JpaRepository<Space, UUID> {
    List<Space> findByWorkspaceId(Long workspace_id);
}