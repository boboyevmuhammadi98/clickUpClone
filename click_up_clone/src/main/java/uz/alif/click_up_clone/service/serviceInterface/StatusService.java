package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.FolderDto;
import uz.alif.click_up_clone.dtos.FolderUserDto;
import uz.alif.click_up_clone.dtos.StatusDto;
import uz.alif.click_up_clone.entity.Folder;
import uz.alif.click_up_clone.entity.Status;
import uz.alif.click_up_clone.entity.User;

import java.util.List;
import java.util.UUID;

public interface StatusService {

    ApiResponse add(StatusDto statusDto);

}
