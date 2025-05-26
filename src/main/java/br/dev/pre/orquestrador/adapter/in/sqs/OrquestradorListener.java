package br.dev.pre.orquestrador.adapter.in.sqs;

import br.dev.pre.orquestrador.domain.strategies.context.Context;
import br.dev.pre.orquestrador.domain.strategies.context.ContextFactory;
import io.awspring.cloud.sqs.annotation.SqsListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class OrquestradorListener {

    private static final Logger log = LoggerFactory.getLogger(OrquestradorListener.class);
    private final ContextFactory contextFactory;
    private Context context;

    public OrquestradorListener(ContextFactory contextFactory) {
        this.contextFactory = contextFactory;
    }

    @EventListener(ApplicationStartedEvent.class)
    public void onStartup() {
        this.context = contextFactory.create("sqs.orquestrador");
        log.info("🟢 SQS listener initialization started");
    }

    @SqsListener("${sqs.orquestrador.queue-name}")
    public void listen(Message<String> message) {
        context.run(message);
    }
}
