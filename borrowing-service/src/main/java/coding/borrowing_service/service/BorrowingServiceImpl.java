package coding.borrowing_service.service;


import coding.borrowing_service.dto.BorrowingEvent;
import coding.borrowing_service.dto.BorrowingRequestDto;
import coding.borrowing_service.dto.BorrowingResponseDto;
import coding.borrowing_service.entity.Borrowing;
import coding.borrowing_service.kafka.BorrowingProducer;
import coding.borrowing_service.repository.BorrowingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BorrowingRepository repository;
    private final BorrowingProducer producer;

    @Override
    public BorrowingResponseDto borrowBook(BorrowingRequestDto request) {
        Borrowing borrowing = new Borrowing(
                request.getBookId(),
                request.getReaderId(),
                "BORROWED",
                Instant.now()
        );
        Borrowing saved = repository.save(borrowing);
        // publish event
        producer.sendBorrowingEvent(new BorrowingEvent(
                saved.getBookId(), saved.getReaderId(), saved.getAction(), saved.getTimestamp()
        ));
        return toDto(saved);
    }

    @Override
    public BorrowingResponseDto returnBook(BorrowingRequestDto request) {
        Borrowing borrowing = new Borrowing(
                request.getBookId(),
                request.getReaderId(),
                "RETURNED",
                Instant.now()
        );
        Borrowing saved = repository.save(borrowing);
        producer.sendBorrowingEvent(new BorrowingEvent(
                saved.getBookId(), saved.getReaderId(), saved.getAction(), saved.getTimestamp()
        ));
        return toDto(saved);
    }

    @Override
    public List<BorrowingResponseDto> getAll() {
        return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private BorrowingResponseDto toDto(Borrowing b) {
        return new BorrowingResponseDto(
                b.getId(), b.getBookId(), b.getReaderId(), b.getAction(), b.getTimestamp()
        );
    }
}