package coding.reader_service.service;

import coding.reader_service.dto.ReaderRegistrationDto;
import coding.reader_service.dto.ReaderResponseDto;

import java.util.List;

public interface ReaderService {
    ReaderResponseDto registerReader(ReaderRegistrationDto registrationDto);
    ReaderResponseDto getReaderById(Long id);
    List<ReaderResponseDto> getAllReaders();
}
