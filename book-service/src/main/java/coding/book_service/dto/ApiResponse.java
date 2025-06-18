package coding.book_service.dto;

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

    private ApiResponse(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.getReasonPhrase();
        this.statusCode = httpStatus.value();
        this.message = message;
        this.generatedAt = Instant.now().getEpochSecond();
        this.data = data;
    }

    public static <T> ApiResponse<T> of(HttpStatus httpStatus, String message, T data) {
        return new ApiResponse<>(httpStatus, message, data);
    }

    public static <T> ApiResponse<T> ok(String message, T data) {
        return of(HttpStatus.OK, message, data);
    }

    public static <T> ApiResponse<T> created(String message, T data) {
        return of(HttpStatus.CREATED, message, data);
    }

    public static <T> ApiResponse<T> notFound(String message) {
        return of(HttpStatus.NOT_FOUND, message, null);
    }

    public static <T> ApiResponse<T> internalError(String message) {
        return of(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
    }
}
