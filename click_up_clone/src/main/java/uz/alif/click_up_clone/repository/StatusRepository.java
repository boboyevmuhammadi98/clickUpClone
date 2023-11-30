package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.Folder;
import uz.alif.click_up_clone.entity.Status;
import uz.alif.click_up_clone.enums.StatusType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<Status, UUID> {

    List<Status> findAllBySpaceI(UUID space_id);

    Optional<Status> findByStatusType(StatusType statusType);

}