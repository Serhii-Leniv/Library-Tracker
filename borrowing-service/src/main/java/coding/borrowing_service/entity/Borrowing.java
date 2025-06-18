package coding.borrowing_service.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private Long readerId;
    private String action; // "BORROWED" or "RETURNED"
    private Instant timestamp;
}