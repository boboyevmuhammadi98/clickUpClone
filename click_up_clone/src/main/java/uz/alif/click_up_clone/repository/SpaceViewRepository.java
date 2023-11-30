package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.SpaceView;

import java.util.List;
import java.util.UUID;

public interface SpaceViewRepository extends JpaRepository<SpaceView, UUID> {
    List<SpaceView> findAllBySpaceId(UUID space_id);
}