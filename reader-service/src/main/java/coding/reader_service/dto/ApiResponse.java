package coding.reader_service.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@Getter
@Setter
public class ApiResponse<T> {

    private String status;      // Наприклад: "OK", "CREATED", "NOT FOUND"
    private int statusCode;     // Наприклад: 200, 404
    private String message;     // Твій власний текст, напр.: "Reader created successfully"
    private long generatedAt;   // Час у UNIX-форматі
    private T data;             // Дані

    private ApiResponse(HttpStatus httpStatus, String message, T data) {
        this.status = httpStatus.getReasonPhrase(); // Наприклад, "OK", "Not Found"
        this.statusCode = httpStatus.value();       // 200, 404, ...
        this.message = message;
        this.generatedAt = Instant.now().getEpochSecond();
        this.data = data;
    }

    // Фабрики
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
