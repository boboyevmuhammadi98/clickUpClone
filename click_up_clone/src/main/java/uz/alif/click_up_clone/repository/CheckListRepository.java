package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.CheckList;

import java.util.UUID;

public interface CheckListRepository extends JpaRepository<CheckList, UUID> {
}