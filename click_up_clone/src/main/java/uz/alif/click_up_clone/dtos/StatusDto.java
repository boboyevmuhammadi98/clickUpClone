package uz.alif.click_up_clone.dtos;

import jakarta.persistence.*;
import lombok.Data;
import uz.alif.click_up_clone.entity.Folder;
import uz.alif.click_up_clone.entity.Space;
import uz.alif.click_up_clone.enums.AccessType;
import uz.alif.click_up_clone.enums.StatusType;

import java.util.UUID;

@Data
public class StatusDto {
    private String name;

    private String color;

    private UUID spaceID;

    private StatusType statusType;
}
