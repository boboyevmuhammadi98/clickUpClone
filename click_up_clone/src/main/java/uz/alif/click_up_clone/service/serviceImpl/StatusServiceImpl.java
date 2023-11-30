package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.*;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.enums.TaskPermission;
import uz.alif.click_up_clone.enums.UserAddType;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.FolderService;
import uz.alif.click_up_clone.service.serviceInterface.SpaceService;
import uz.alif.click_up_clone.service.serviceInterface.StatusService;

import java.util.List;
import java.util.UUID;

@Service
public class StatusServiceImpl implements StatusService {

    private final StatusRepository statusRepository;
    private final SpaceRepository spaceRepository;
    private final FolderRepository folderRepository;

    public StatusServiceImpl(StatusRepository statusRepository,
                             SpaceRepository spaceRepository,
                             FolderRepository folderRepository) {
        this.statusRepository = statusRepository;
        this.spaceRepository = spaceRepository;
        this.folderRepository = folderRepository;
    }
    @Override
    public ApiResponse add(StatusDto statusDto) {
        Space space = spaceRepository.findById(statusDto.getSpaceID()).orElseThrow(() -> new ResourceNotFoundException("space"));
        Status save = statusRepository.save(new Status(statusDto.getName(), statusDto.getColor(), space, statusDto.getStatusType()));
        return new ApiResponse("created", true, 201, save);
    }
}
