package coding.borrowing_service.service;

import coding.borrowing_service.dto.BorrowingRequestDto;
import coding.borrowing_service.dto.BorrowingResponseDto;

import java.util.List;

public interface BorrowingService {
    BorrowingResponseDto borrowBook(BorrowingRequestDto request);
    BorrowingResponseDto returnBook(BorrowingRequestDto request);
    List<BorrowingResponseDto> getAll();
}
