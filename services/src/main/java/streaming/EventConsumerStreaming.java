package streaming;

import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;


@ApplicationScoped
public class EventConsumerStreaming {

    private static final Logger log = Logger.getLogger(EventConsumerStreaming.class.getName());

    private static final String TOPIC_NAME = "h2ihozli-image-upload";

    @StreamListener(topics = {TOPIC_NAME}, config = "consumer")
    public void onImageUploadMessage(ConsumerRecord<String, String> record) {
        log.info(record.key().toString());
    }
}
