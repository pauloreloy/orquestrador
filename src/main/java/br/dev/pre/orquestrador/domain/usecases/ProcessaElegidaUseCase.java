package br.dev.pre.orquestrador.domain.usecases;

import br.dev.pre.orquestrador.adapter.out.aws.StepFunctionImpl;
import br.dev.pre.orquestrador.adapter.out.aws.interfaces.DynamoDbRepository;
import br.dev.pre.orquestrador.adapter.out.aws.interfaces.StepFunction;
import br.dev.pre.orquestrador.adapter.out.controlador.interfaces.ControladorClient;
import br.dev.pre.orquestrador.domain.entities.PortabilidadeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessaElegidaUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessaElegidaUseCase.class);
    private final StepFunction stepFunction;
    private final DynamoDbRepository dynamoDbRepository;

    public ProcessaElegidaUseCase(StepFunction stepFunction, DynamoDbRepository dynamoDbRepository) {
        this.stepFunction = stepFunction;
        this.dynamoDbRepository = dynamoDbRepository;
    }

    public void execute(PortabilidadeEntity portabilidade) {
        dynamoDbRepository.savePortabilidade(portabilidade,
                "elegida",
                "in_progress",
                null);
        //stepFunction.startExecution(portabilidade.getCodigoIdentificadorPortabilidade());
    }

}