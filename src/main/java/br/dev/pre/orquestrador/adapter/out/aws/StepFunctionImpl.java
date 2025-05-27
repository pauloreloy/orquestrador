package br.dev.pre.orquestrador.adapter.out.aws;

import br.dev.pre.orquestrador.adapter.out.aws.interfaces.StepFunction;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.sfn.SfnClient;
import software.amazon.awssdk.services.sfn.model.ListStateMachinesResponse;
import software.amazon.awssdk.services.sfn.model.StartExecutionRequest;
import software.amazon.awssdk.services.sfn.model.StartExecutionResponse;
import software.amazon.awssdk.services.sfn.model.StateMachineListItem;

import java.util.Optional;

@Component
public class StepFunctionImpl implements StepFunction {

    private final SfnClient sfnClient;

    @Value("${stepfunction.arn}")
    private String arn;

    public StepFunctionImpl(SfnClient sfnClient) {
        this.sfnClient = sfnClient;
    }

    @Override
    public Optional<String> findArnByName(String name) {
        ListStateMachinesResponse response = sfnClient.listStateMachines();
        return response.stateMachines().stream()
                .filter(machine -> machine.name().equals(name))
                .map(StateMachineListItem::stateMachineArn)
                .findFirst();
    }

    @Override
    public void listStateMachines() {
        ListStateMachinesResponse response = sfnClient.listStateMachines();
        response.stateMachines().forEach(machine -> {
            System.out.println("Name: " + machine.name() + ", ARN: " + machine.stateMachineArn());
        });
    }

    @Override
    public void startExecution(String inputJson) {
        StartExecutionRequest request = StartExecutionRequest.builder()
                .stateMachineArn(arn)
                .input(inputJson)
                .build();

        StartExecutionResponse response = sfnClient.startExecution(request);
        System.out.println(response);
    }

}
