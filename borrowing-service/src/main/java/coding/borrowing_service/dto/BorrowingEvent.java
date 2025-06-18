package coding.borrowing_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BorrowingEvent {
    private Long bookId;
    private Long readerId;
    private String action;
    private Instant timestamp;
}