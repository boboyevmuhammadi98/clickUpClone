package uz.alif.click_up_clone.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private boolean success;
    @JsonIgnore
    private int code;
    private Object data;

    public ApiResponse(String message, boolean success, int code) {
        this.message = message;
        this.success = success;
        this.code = code;
    }

    public ApiResponse(String message, boolean success, int code, Object data) {
        this.message = message;
        this.success = success;
        this.code = code;
        this.data = data;
    }
}
