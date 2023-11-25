package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.SpaceDto;
import uz.alif.click_up_clone.entity.Space;
import uz.alif.click_up_clone.entity.User;

import java.util.List;
import java.util.UUID;

public interface SpaceService {
    List<Space> getSpaceByWorkspaceId(Long id);

    ApiResponse add(Long id, SpaceDto spaceDto, User owner);

    ApiResponse edit(UUID id, SpaceDto spaceDto);

    ApiResponse delete(UUID id);

}
