package br.dev.pre.orquestrador.adapter.out.aws;

import br.dev.pre.orquestrador.adapter.out.aws.interfaces.DynamoDbRepository;
import br.dev.pre.orquestrador.domain.entities.DbOrquestradorPortabilidadeEntity;
import br.dev.pre.orquestrador.domain.entities.PortabilidadeEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Repository
@Component
public class DynamoDbRepositoryImpl implements DynamoDbRepository {

    private final DynamoDbTable<DbOrquestradorPortabilidadeEntity> table;

    public DynamoDbRepositoryImpl(DynamoDbClient dynamoDbClient,
                                  @Value("${spring.cloud.aws.dynamodb.table_name}") String tableName) {
        DynamoDbEnhancedClient enhancedClient = DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
        this.table = enhancedClient.table(tableName, TableSchema.fromBean(DbOrquestradorPortabilidadeEntity.class));
    }

    @Override
    public void savePortabilidade(PortabilidadeEntity portabilidade,
                                  String stepLabel,
                                  String stepStatus,
                                  String stepToken) {
        table.putItem(DbOrquestradorPortabilidadeEntity.builder()
                .codigo_identificador_portabilidade(portabilidade.getCodigoIdentificadorPortabilidade())
                .step_label(stepLabel)
                .step_status(stepStatus)
                .step_token(stepToken)
                .build());
    }
}