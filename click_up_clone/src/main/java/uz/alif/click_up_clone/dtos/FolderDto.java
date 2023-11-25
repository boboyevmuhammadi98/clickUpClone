package uz.alif.click_up_clone.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import uz.alif.click_up_clone.entity.Space;
import uz.alif.click_up_clone.enums.AccessType;

import java.util.UUID;

@Data
public class FolderDto {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String color;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private UUID spaceId;

    @Column(nullable = false)
    private AccessType accessType;
}
