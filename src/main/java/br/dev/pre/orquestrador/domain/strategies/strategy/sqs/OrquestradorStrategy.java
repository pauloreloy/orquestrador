package br.dev.pre.orquestrador.domain.strategies.strategy.sqs;

import br.dev.pre.orquestrador.domain.entities.SqsEntity;
import br.dev.pre.orquestrador.domain.strategies.StrategyBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class OrquestradorStrategy extends SqsBase implements StrategyBase {

    private static final Logger log = LoggerFactory.getLogger(OrquestradorStrategy.class);

    @Override
    public String getStrategyName() {
        return "sqs.orquestrador";
    }

    public void run(Object... data) {
        if (data[0] instanceof Message<?> message) {
            @SuppressWarnings("unchecked")
            SqsEntity sqsMessage = SqsEntity.from((Message<String>) message);
            log.info("📩 Received SQS message payload: [{}]", sqsMessage.getBody());
            log.info("🔍 Headers: {}", sqsMessage.getHeaders());
            log.info("🧾 MessageId: {}", sqsMessage.getMessageId());
        }
    }
}
