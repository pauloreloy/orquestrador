package br.dev.pre.orquestrador.adapter.out.aws.interfaces;

import br.dev.pre.orquestrador.domain.entities.PortabilidadeEntity;

public interface DynamoDbRepository {
    void savePortabilidade(PortabilidadeEntity portabilidade,
                           String stepLabel,
                           String stepStatus,
                           String stepToken);
}
