package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.WorkspaceDto;
import uz.alif.click_up_clone.dtos.WorkspaceRoleDto;
import uz.alif.click_up_clone.dtos.WorkspaceUserDto;
import uz.alif.click_up_clone.entity.User;
import uz.alif.click_up_clone.entity.Workspace;
import uz.alif.click_up_clone.entity.WorkspaceUser;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WorkspaceService {
    ApiResponse add(WorkspaceDto workspaceDto, User user);

    ApiResponse edit(Long id, WorkspaceDto workspaceDto);

    ApiResponse delete(Long id);

    ApiResponse changeOwner(Long id, UUID userId);

    ApiResponse addOrEditOrRemoveWorkspaceUser(Long id, WorkspaceUserDto workspaceUserDto);

    ApiResponse joinWorkspace(Long id, User user);

    List<WorkspaceUser> getWorkspaceUserByWorkspaceId(Long id);

    List<Workspace> getWorkspaceByUser(User user);

    ApiResponse addRoleToWorkspace(Long id, WorkspaceRoleDto workspaceRoleDto);
}
