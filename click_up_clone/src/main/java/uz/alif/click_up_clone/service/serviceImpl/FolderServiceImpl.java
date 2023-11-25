package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.*;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.enums.TaskPermission;
import uz.alif.click_up_clone.enums.UserAddType;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.FolderService;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Service
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final SpaceRepository spaceRepository;
    private final FolderUserRepository folderUserRepository;

    public FolderServiceImpl(FolderRepository folderRepository,
                             SpaceRepository spaceRepository,
                             FolderUserRepository folderUserRepository) {
        this.folderRepository = folderRepository;
        this.spaceRepository = spaceRepository;
        this.folderUserRepository = folderUserRepository;
    }

    @Override
    public List<Folder> getFolderBySpaceId(UUID id) {
        return folderRepository.findAllBySpaceId(id);
    }

    @Override
    public ApiResponse add(UUID id, FolderDto folderDto, User user) {
        Space space = spaceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("space"));
        Folder save = folderRepository.save(new Folder(
                folderDto.getName(),
                folderDto.getColor(),
                space,
                folderDto.getAccessType(),
                false

        ));

        folderUserRepository.save(new FolderUser(
                save,
                user,
                TaskPermission.FULL_ACCESS
        ));
        return new ApiResponse("created", true, 201, save);
    }

    @Override
    public ApiResponse edit(UUID id, FolderDto folderDto) {
        Folder folder = folderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("folder"));
        folder.setName(folderDto.getName());
        folder.setColor(folderDto.getColor());
        folder.setAccessType(folderDto.getAccessType());
        folder.setArchived(folder.isArchived());
        Folder save = folderRepository.save(folder);
        return new ApiResponse("edited", true, 201, save);
    }

    @Override
    public ApiResponse delete(UUID id) {
        try {
            folderRepository.deleteById(id);
            return new ApiResponse("deleted", true, 404, null);
        } catch (Exception e) {
            return new ApiResponse("can not delete", false, 404, null);
        }
    }

    @Override
    public ApiResponse addOrEditOrRemoveFolderUser(UUID id, FolderUserDto folderUserDto) {
        return null;
    }


}
