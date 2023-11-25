package uz.alif.click_up_clone.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uz.alif.click_up_clone.entity.Icon;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.entity.Workspace;
import uz.alif.click_up_clone.enums.AccessType;

import java.util.UUID;

@Data
public class SpaceDto {

    private String name;


    private String color;


    private String initialLetter;


    private Long workspaceId;


    private UUID iconId;


    private AccessType accessType;
}
