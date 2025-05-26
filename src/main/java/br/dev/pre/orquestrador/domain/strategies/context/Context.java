package br.dev.pre.orquestrador.domain.strategies.context;
import br.dev.pre.orquestrador.domain.strategies.StrategyBase;

public class Context {

    private final StrategyBase strategy;

    public Context(StrategyBase strategy) {
        if (strategy == null) {
            throw new IllegalArgumentException("Strategy cannot be null");
        }
        this.strategy = strategy;
    }

    public void run(Object... data) {
        strategy.run(data);
    }
}