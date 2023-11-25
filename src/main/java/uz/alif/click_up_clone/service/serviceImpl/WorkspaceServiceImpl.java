package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.dtos.WorkspaceDto;
import uz.alif.click_up_clone.dtos.WorkspaceRoleDto;
import uz.alif.click_up_clone.dtos.WorkspaceUserDto;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.enums.WorkspacePermissionName;
import uz.alif.click_up_clone.enums.WorkspaceRoleName;
import uz.alif.click_up_clone.enums.WorkspaceUserAddType;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.WorkspaceService;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class WorkspaceServiceImpl implements WorkspaceService {

    @Autowired
    WorkspaceRepository workspaceRepository;
    @Autowired
    private AttachmentRepository attachmentRepository;
    @Autowired
    private WorkspaceUserRepository workspaceUserRepository;
    @Autowired
    private WorkspaceRoleRepository workspaceRoleRepository;
    @Autowired
    private WorkspacePermissionRepository workspacePermissionRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ApiResponse add(WorkspaceDto workspaceDto, User user) {
        if (workspaceRepository.existsByOwnerIdAndName(user.getId(), workspaceDto.getName()))
            return new ApiResponse("this workspace name using Owner already exists", false, 409, null);
        Workspace workspace = new Workspace(
                workspaceDto.getName(),
                workspaceDto.getColor(),
                user,
                workspaceDto.getAttachmentId() == null ? null : attachmentRepository.findById(workspaceDto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("attachment not found"))
        );
        Workspace save = workspaceRepository.save(workspace);

        WorkspaceRole workspaceRoleOwner = workspaceRoleRepository.save(new WorkspaceRole(
                WorkspaceRoleName.ROLE_OWNER.name,
                workspace,
                null
        ));

        WorkspaceRole workspaceRoleAdmin = workspaceRoleRepository.save(new WorkspaceRole(
                WorkspaceRoleName.ROLE_ADMIN.name,
                workspace,
                null
        ));

        WorkspaceRole workspaceRoleMember = workspaceRoleRepository.save(new WorkspaceRole(
                WorkspaceRoleName.ROLE_MEMBER.name,
                workspace,
                null
        ));

        WorkspaceRole workspaceRoleGuest = workspaceRoleRepository.save(new WorkspaceRole(
                WorkspaceRoleName.ROLE_GUEST.name,
                workspace,
                null
        ));

        List<WorkspacePermission> workspacePermissions = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : WorkspacePermissionName.values()) {
            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_OWNER)) {
                workspacePermissions.add(new WorkspacePermission(workspaceRoleOwner, workspacePermissionName));
            }

            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_ADMIN)) {
                workspacePermissions.add(new WorkspacePermission(workspaceRoleAdmin, workspacePermissionName));
            }

            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_MEMBER)) {
                workspacePermissions.add(new WorkspacePermission(workspaceRoleMember, workspacePermissionName));
            }

            if (workspacePermissionName.getWorkspaceRoleNames().contains(WorkspaceRoleName.ROLE_GUEST)) {
                workspacePermissions.add(new WorkspacePermission(workspaceRoleGuest, workspacePermissionName));
            }
        }
        workspacePermissionRepository.saveAll(workspacePermissions);

        workspaceUserRepository.save(
                new WorkspaceUser(
                        save,
                        user,
                        workspaceRoleOwner,
                        new Timestamp(System.currentTimeMillis()),
                        new Timestamp(System.currentTimeMillis())
                )
        );

        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse edit(Long id, WorkspaceDto workspaceDto) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        workspace.setName(workspaceDto.getName());
        workspace.setColor(workspaceDto.getColor());
        workspace.setAvatar(workspaceDto.getAttachmentId() == null ? null : attachmentRepository.findById(workspaceDto.getAttachmentId()).orElseThrow(() -> new ResourceNotFoundException("attachment")));
        Workspace save = workspaceRepository.save(workspace);
        return new ApiResponse("saved", true, 201, save);
    }

    @Override
    public ApiResponse delete(Long id) {
        try {
            workspaceRepository.deleteById(id);
            return new ApiResponse("deleted", true, 404, null);
        } catch (Exception e) {
            return new ApiResponse("can not delete workspace", false, 404, null);
        }
    }

    @Override
    public ApiResponse changeOwner(Long id, UUID userId) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, userId).orElseThrow(() -> new ResourceNotFoundException("workspace or user not found"));
        workspace.setOwner(workspaceUser.getUser());
        Workspace save = workspaceRepository.save(workspace);
        return new ApiResponse("changed", true, 201, save);
    }

    @Override
    public ApiResponse addOrEditOrRemoveWorkspaceUser(Long id, WorkspaceUserDto workspaceUserDto) {
        if (workspaceUserDto.getWorkspaceUserAddType().equals(WorkspaceUserAddType.ADD)) {
            WorkspaceUser save = workspaceUserRepository.save(new WorkspaceUser(
                    workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("workspace")),
                    userRepository.findById(workspaceUserDto.getMemberId()).orElseThrow(() -> new ResourceNotFoundException("user")),
                    workspaceRoleRepository.findById(workspaceUserDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("role")),
                    new Timestamp(System.currentTimeMillis()),
                    null
            ));

            return new ApiResponse("created", true, 201, save);
        } else if (workspaceUserDto.getWorkspaceUserAddType().equals(WorkspaceUserAddType.EDIT)) {
            WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, workspaceUserDto.getMemberId()).orElseThrow(() -> new ResourceNotFoundException("user"));
            workspaceUser.setWorkspaceRole(workspaceRoleRepository.findById(workspaceUserDto.getRoleId()).orElseThrow(() -> new ResourceNotFoundException("role")));
            WorkspaceUser save = workspaceUserRepository.save(workspaceUser);
            return new ApiResponse("changed", true, 201, save);
        } else if (workspaceUserDto.getWorkspaceUserAddType().equals(WorkspaceUserAddType.DELETE)) {
            workspaceUserRepository.deleteByWorkspaceIdAndUserId(id, workspaceUserDto.getMemberId());
        }
        return new ApiResponse("un suppoted workspaceuseraddtype", false, 400, null);
    }

    @Override
    public ApiResponse joinWorkspace(Long id, User user) {
        WorkspaceUser workspaceUser = workspaceUserRepository.findByWorkspaceIdAndUserId(id, user.getId()).orElseThrow(() -> new ResourceNotFoundException("workspace or user not found"));
        workspaceUser.setDateJoined(new Timestamp(System.currentTimeMillis()));
        WorkspaceUser save = workspaceUserRepository.save(workspaceUser);
        return new ApiResponse("joined", true, 201, null);
    }

    @Override
    public List<WorkspaceUser> getWorkspaceUserByWorkspaceId(Long id) {
        return workspaceUserRepository.findByWorkspaceId(id);
    }

    @Override
    public List<Workspace> getWorkspaceByUser(User user) {
        return workspaceRepository.findAllByOwner(user);
    }

    @Override
    public ApiResponse addRoleToWorkspace(Long id, WorkspaceRoleDto workspaceRoleDto) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        WorkspaceRole workspaceRole = workspaceRoleRepository.save(new WorkspaceRole(
                workspaceRoleDto.getName(),
                workspace,
                workspaceRoleDto.getExtendsRole()
        ));
        List<WorkspacePermission> workspacePermissions = new ArrayList<>();
        for (WorkspacePermissionName workspacePermissionName : workspaceRoleDto.getWorkspacePermissionNames()) {
            workspacePermissions.add(new WorkspacePermission(workspaceRole, workspacePermissionName));
        }
        workspacePermissionRepository.saveAll(workspacePermissions);
        return new ApiResponse("created", true, 201, null);
    }
}
