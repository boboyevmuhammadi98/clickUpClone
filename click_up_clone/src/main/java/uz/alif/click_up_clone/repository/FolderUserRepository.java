package uz.alif.click_up_clone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.alif.click_up_clone.entity.FolderUser;

import java.util.UUID;

public interface FolderUserRepository extends JpaRepository<FolderUser, UUID> {
}