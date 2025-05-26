package br.dev.pre.orquestrador.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DbOrquestradorPortabilidadeEntity {
    private String codigo_identificador_portabilidade;
    private String step_label;
    private String step_status;
    private String step_token;

    @DynamoDbPartitionKey
    public String getCodigo_identificador_portabilidade() {
        return codigo_identificador_portabilidade;
    }
}