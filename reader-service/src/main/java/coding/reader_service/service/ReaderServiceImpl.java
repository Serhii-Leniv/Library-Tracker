
package coding.reader_service.service;

import coding.reader_service.dto.ReaderRegistrationDto;
import coding.reader_service.dto.ReaderResponseDto;
import coding.reader_service.entity.Reader;
import coding.reader_service.repository.ReaderRepository;
import coding.reader_service.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {


    private final ReaderRepository readerRepository;



    @Override
    public ReaderResponseDto registerReader(ReaderRegistrationDto registrationDto) {
        // перевірка, чи вже існує користувач
        if (readerRepository.findByEmail(registrationDto.getEmail()) != null) {
            throw new RuntimeException("Reader with this email already exists");
        }

        Reader reader = new Reader();
        reader.setName(registrationDto.getName());
        reader.setEmail(registrationDto.getEmail());
        reader.setPassword(registrationDto.getPassword()); // поки без шифрування

        Reader saved = readerRepository.save(reader);

        return mapToResponseDto(saved);
    }

    @Override
    public ReaderResponseDto getReaderById(Long id) {
        Reader reader = readerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reader not found"));

        return mapToResponseDto(reader);
    }

    @Override
    public List<ReaderResponseDto> getAllReaders() {
        return readerRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    private ReaderResponseDto mapToResponseDto(Reader reader) {
        ReaderResponseDto dto = new ReaderResponseDto();
        dto.setId(reader.getId());
        dto.setName(reader.getName());
        dto.setEmail(reader.getEmail());
        return dto;
    }
}
