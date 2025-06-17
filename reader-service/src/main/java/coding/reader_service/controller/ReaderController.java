package coding.reader_service.controller;


import coding.reader_service.dto.*;
import coding.reader_service.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/readers")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<ReaderResponseDto>> register(@RequestBody ReaderRegistrationDto dto) {
        ReaderResponseDto registered = readerService.registerReader(dto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponse.created("Reader registered successfully", registered));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReaderResponseDto>> getById(@PathVariable Long id) {
        ReaderResponseDto reader = readerService.getReaderById(id);
        return ResponseEntity
                .ok(ApiResponse.ok("Reader found", reader));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ReaderResponseDto>>> getAll() {
        List<ReaderResponseDto> readers = readerService.getAllReaders();
        return ResponseEntity
                .ok(ApiResponse.ok("All readers fetched", readers));
    }
}

