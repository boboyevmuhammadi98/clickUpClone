package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.FolderDto;
import uz.alif.click_up_clone.dtos.FolderUserDto;
import uz.alif.click_up_clone.dtos.SpaceDto;
import uz.alif.click_up_clone.entity.Folder;
import uz.alif.click_up_clone.entity.Space;
import uz.alif.click_up_clone.entity.User;

import java.util.List;
import java.util.UUID;

public interface FolderService {
    List<Folder> getFolderBySpaceId(UUID id);

    ApiResponse add(UUID id, FolderDto folderDto, User user);

    ApiResponse edit(UUID id, FolderDto folderDto);

    ApiResponse delete(UUID id);

    ApiResponse addOrEditOrRemoveFolderUser(UUID id, FolderUserDto folderUserDto);
}
