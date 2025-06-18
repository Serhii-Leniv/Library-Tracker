package coding.borrowing_service.controller;


import coding.borrowing_service.dto.ApiResponse;
import coding.borrowing_service.dto.BorrowingRequestDto;
import coding.borrowing_service.dto.BorrowingResponseDto;
import coding.borrowing_service.service.BorrowingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingController {
    private final BorrowingService service;

    @PostMapping("/borrow")
    public ResponseEntity<ApiResponse<BorrowingResponseDto>> borrow(@RequestBody BorrowingRequestDto dto) {
        BorrowingResponseDto result = service.borrowBook(dto);
        return ResponseEntity.ok(ApiResponse.success("Book borrowed", result));
    }

    @PostMapping("/return")
    public ResponseEntity<ApiResponse<BorrowingResponseDto>> returnBook(@RequestBody BorrowingRequestDto dto) {
        BorrowingResponseDto result = service.returnBook(dto);
        return ResponseEntity.ok(ApiResponse.success("Book returned", result));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BorrowingResponseDto>>> getAll() {
        List<BorrowingResponseDto> list = service.getAll();
        return ResponseEntity.ok(ApiResponse.success("All borrowings", list));
    }
}
