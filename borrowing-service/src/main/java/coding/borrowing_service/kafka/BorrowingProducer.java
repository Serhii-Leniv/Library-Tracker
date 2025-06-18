package coding.borrowing_service.kafka;

import coding.borrowing_service.dto.BorrowingEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowingProducer {

    private final KafkaTemplate<String, BorrowingEvent> template;
    private static final String TOPIC = "borrowing-events";

    public void sendBorrowingEvent(BorrowingEvent event) {
        template.send(TOPIC, event);
    }
}