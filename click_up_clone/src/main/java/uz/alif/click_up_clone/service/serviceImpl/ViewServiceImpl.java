package uz.alif.click_up_clone.service.serviceImpl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import uz.alif.click_up_clone.dtos.ApiResponse;
import uz.alif.click_up_clone.entity.*;
import uz.alif.click_up_clone.repository.*;
import uz.alif.click_up_clone.service.serviceInterface.ChecklistService;
import uz.alif.click_up_clone.service.serviceInterface.ViewService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ViewServiceImpl implements ViewService {

    private final SpaceViewRepository spaceViewRepository;

    public ViewServiceImpl(SpaceViewRepository spaceViewRepository) {
        this.spaceViewRepository = spaceViewRepository;
    }

    @Override
    public ApiResponse getAllViewBySpace(UUID spaceId) {
        List<SpaceView> spaceViews = spaceViewRepository.findAllBySpaceId(spaceId);
        List<View> collect = spaceViews.stream().map(SpaceView::getView).toList();
        return new ApiResponse("success", true, 200, collect);
    }

}