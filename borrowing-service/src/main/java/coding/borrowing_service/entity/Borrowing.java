package coding.borrowing_service.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "borrowings")
public class Borrowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private Long readerId;
    private String action; // "BORROWED" or "RETURNED"
    private Instant timestamp;

    public Borrowing(Long bookId, Long readerId, String action, Instant timestamp) {
        this.bookId = bookId;
        this.readerId = readerId;
        this.action = action;
        this.timestamp = timestamp;
    }

}