package br.dev.pre.orquestrador.domain.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PortabilidadeEntity {

    @JsonProperty("codigo_identificador_portabilidade")
    private String codigoIdentificadorPortabilidade;

    public static PortabilidadeEntity from(String codigoIdentificadorPortabilidade) {
        return PortabilidadeEntity.builder()
                .codigoIdentificadorPortabilidade(codigoIdentificadorPortabilidade)
                .build();
    }

    @Override
    public String toString() {
        return "PortabilidadeEntity{" +
                "codigoIdentificadorPortabilidade='" + codigoIdentificadorPortabilidade + '\'' +
                '}';
    }

    public String toJson() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to convert PortabilidadeEntity to JSON", e);
        }
    }

}
