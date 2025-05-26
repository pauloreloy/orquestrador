package br.dev.pre.orquestrador.domain.usecases;

import br.dev.pre.orquestrador.adapter.out.aws.StepFunctionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ProcessaElegidaUseCase {

    private static final Logger log = LoggerFactory.getLogger(ProcessaElegidaUseCase.class);
    private final StepFunctionService stepFunctionService;

    public ProcessaElegidaUseCase(StepFunctionService stepFunctionService) {
        this.stepFunctionService = stepFunctionService;
    }

    public void execute(String input) {
        stepFunctionService.listStateMachines();
    }

}