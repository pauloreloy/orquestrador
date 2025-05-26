package br.dev.pre.orquestrador.domain.strategies.strategy.topics;

import br.dev.pre.orquestrador.domain.entities.PortabilidadeEntity;
import br.dev.pre.orquestrador.domain.entities.TopicEntity;
import br.dev.pre.orquestrador.domain.strategies.StrategyBase;
import br.dev.pre.orquestrador.domain.usecases.ProcessaElegidaUseCase;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;


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
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
        PortabilidadeEntity portabilidade = null;
        try {
            portabilidade = mapper.readValue(topicData.getMessage(), PortabilidadeEntity.class);
        } catch (JsonProcessingException e) {
            log.error("Erro ao desserializar a mensagem: {}", topicData.getMessage(), e);
        }
        assert portabilidade != null;
        processaElegidaUseCase.execute(portabilidade);
    }
}
