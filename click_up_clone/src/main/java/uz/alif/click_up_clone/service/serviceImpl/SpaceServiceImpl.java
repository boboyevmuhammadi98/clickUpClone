package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.*;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.SpaceService;

import java.util.List;
import java.util.UUID;

@Service
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;
    private final WorkspaceRepository workspaceRepository;
    private final IconRepository iconRepository;

    public SpaceServiceImpl(SpaceRepository spaceRepository,
                            WorkspaceRepository workspaceRepository,
                            IconRepository iconRepository) {
        this.spaceRepository = spaceRepository;
        this.workspaceRepository = workspaceRepository;
        this.iconRepository = iconRepository;
    }

    @Override
    public List<Space> getSpaceByWorkspaceId(Long id) {
        return spaceRepository.findByWorkspaceId(id);
    }

    @Override
    public ApiResponse add(Long id, SpaceDto spaceDto, User owner) {
        Workspace workspace = workspaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("workspace"));
        Space space = spaceRepository.save(new Space(
                spaceDto.getName(),
                spaceDto.getColor(),
                workspace,
                owner,
                iconRepository.findById(spaceDto.getIconId()).orElseThrow(() -> new ResourceNotFoundException("iconId")),
                spaceDto.getAccessType()
        ));
        return new ApiResponse("created", true, 201, space);
    }

    @Override
    public ApiResponse edit(UUID id, SpaceDto spaceDto) {
        Space space = spaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("space"));
        space.setName(spaceDto.getName());
        space.setColor(spaceDto.getColor());
        space.setIcon(iconRepository.findById(spaceDto.getIconId()).orElseThrow(() -> new ResourceNotFoundException("iconId")));
        space.setAccessType(spaceDto.getAccessType());
        Space save = spaceRepository.save(space);
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse delete(UUID id) {
        try {
            spaceRepository.deleteById(id);
            return new ApiResponse("deleted", true, 404, null);
        } catch (Exception e) {
            return new ApiResponse("can not deleted", false, 409, null);
        }
    }
}
