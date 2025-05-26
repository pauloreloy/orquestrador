package br.dev.pre.orquestrador.adapter.in.kafka;

import br.dev.pre.orquestrador.domain.strategies.context.Context;
import br.dev.pre.orquestrador.domain.strategies.context.ContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import org.springframework.context.event.EventListener;

@Component
public class ElegidaListener {

    private static final Logger log = LoggerFactory.getLogger(ElegidaListener.class);
    private final ContextFactory contextFactory;
    private Context context;

    public ElegidaListener(ContextFactory contextFactory) {
        this.contextFactory = contextFactory;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStartup() {
        this.context = contextFactory.create("topics.elegida");
        log.info("🟢 Kafka consumer initialization started");
    }

    @KafkaListener(
            topics = "#{'${kafka.elegida.topic}'}",
            groupId = "your-consumer-group",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void listen(
            String message,
            @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {
        context.run(message, topic, partition, offset);
    }
}
