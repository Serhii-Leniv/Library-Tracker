package coding.borrowing_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BorrowingRequestDto {
    private Long bookId;
    private Long readerId;
}