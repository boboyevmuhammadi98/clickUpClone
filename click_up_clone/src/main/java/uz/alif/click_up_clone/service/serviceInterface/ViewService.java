package uz.alif.click_up_clone.service.serviceInterface;

import uz.alif.click_up_clone.dtos.ApiResponse;

import java.util.UUID;

public interface ViewService {
    ApiResponse getAllViewBySpace(UUID spaceId);
}
