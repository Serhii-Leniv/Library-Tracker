package coding.borrowing_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.Instant;

@Getter
@Setter
public class ApiResponse<T> {
    private String status;
    private int statusCode;
    private String message;
    private long generatedAt;
    private T data;

    private ApiResponse(HttpStatus status, String message, T data) {
        this.status = status.getReasonPhrase();
        this.statusCode = status.value();
        this.message = message;
        this.generatedAt = Instant.now().getEpochSecond();
        this.data = data;
    }

    public static <T> ApiResponse<T> success(String message, T data) {
        return new ApiResponse<>(HttpStatus.OK, message, data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return new ApiResponse<>(HttpStatus.CREATED, message, data);
    }

    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }
}
