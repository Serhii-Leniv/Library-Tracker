package coding.reader_service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReaderRegistrationDto {
    private String name;
    private String email;
    private String password;
}
