package br.dev.pre.orquestrador.domain.strategies.strategy.topics;

import br.dev.pre.orquestrador.domain.entities.TopicEntity;
import br.dev.pre.orquestrador.domain.strategies.StrategyBase;
import br.dev.pre.orquestrador.domain.usecases.ProcessaElegidaUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Component
public class ElegidaStrategy extends TopicBase implements StrategyBase {

    private static final Logger log = LoggerFactory.getLogger(ElegidaStrategy.class);
    private final ProcessaElegidaUseCase processaElegidaUseCase;

    public ElegidaStrategy(ProcessaElegidaUseCase processaElegidaUseCase) {
        this.processaElegidaUseCase = processaElegidaUseCase;
    }

    @Override
    public String getStrategyName() {
        return "topics.elegida";
    }

    public void run(Object... data) {
        TopicEntity topicData = TopicEntity.fromData(data);
        log.info("📩 Received message [{}] from topic [{}], partition [{}], offset [{}]",
                topicData.getMessage(), topicData.getTopic(), topicData.getPartition(), topicData.getOffset()
        );
        try {
            processaElegidaUseCase.execute(topicData.getMessage());
        }
        catch (Exception e) {
            log.error("❌ Error processing message [{}] from topic [{}]: {}",
                      topicData.getMessage(), topicData.getTopic(), e.getMessage());
            throw new RuntimeException("Failed to process message", e);
        }

    }
}
