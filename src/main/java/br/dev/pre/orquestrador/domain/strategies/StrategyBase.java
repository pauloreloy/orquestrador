package br.dev.pre.orquestrador.domain.strategies;

public interface StrategyBase {
    void run(Object... data);
    String getStrategyName();
}
