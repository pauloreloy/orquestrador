package br.dev.pre.orquestrador.adapter.out.aws.interfaces;

import java.util.Optional;


public interface StepFunction {
    Optional<String> findArnByName(String name);
    void listStateMachines();
    void startExecution(String arn, String inputJson);
}