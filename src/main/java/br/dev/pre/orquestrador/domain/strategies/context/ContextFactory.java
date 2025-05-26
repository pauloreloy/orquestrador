package br.dev.pre.orquestrador.domain.strategies.context;

import br.dev.pre.orquestrador.domain.strategies.StrategyBase;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ContextFactory {

    private final Map<String, StrategyBase> strategies = new HashMap<>();

    public ContextFactory(List<StrategyBase> strategyList) {
        for (StrategyBase strategy : strategyList) {
            strategies.put(strategy.getStrategyName(), strategy);
        }
    }

    public Context create(String name) {
        StrategyBase strategy = strategies.get(name);
        if (strategy == null) {
            throw new IllegalArgumentException("No strategy found for: " + name);
        }
        return new Context(strategy);
    }
}
