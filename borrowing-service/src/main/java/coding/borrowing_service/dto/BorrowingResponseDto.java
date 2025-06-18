package coding.borrowing_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
public class BorrowingResponseDto {
    private Long id;
    private Long bookId;
    private Long readerId;
    private String action;
    private Instant timestamp;
}