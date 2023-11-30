package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.CheckListItem;

import java.util.UUID;

public interface CheckListItemRepository extends JpaRepository<CheckListItem, UUID> {
}