package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.View;

import java.util.List;
import java.util.UUID;

public interface ViewRepository extends JpaRepository<View, UUID> {
}