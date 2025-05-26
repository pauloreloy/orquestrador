package br.dev.pre.orquestrador.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicEntity {
    private String  message;
    private String  topic;
    private Integer partition;
    private Long    offset;

    public static TopicEntity fromData(Object... data) {
        return new TopicEntity(
                data[0].toString(),
                data[1].toString(),
                (Integer) data[2],
                (Long) data[3]
        );
    }
}