package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.Icon;

import java.util.UUID;

public interface IconRepository extends JpaRepository<Icon, UUID> {
}