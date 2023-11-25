package uz.alif.click_up_clone.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponseWithToken {
    private String message;
    private boolean success;
    private int code;
    private String token;
}
