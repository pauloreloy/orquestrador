package br.dev.pre.orquestrador.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SqsEntity {
    private String body;
    private String messageId;
    private String receiptHandle;
    private String queueUrl;
    private String queueName;
    private String senderId;
    private int approximateReceiveCount;
    private long sentTimestamp;
    private long approximateFirstReceiveTimestamp;
    private long timestamp;
    private MessageHeaders headers;

    public static SqsEntity from(Message<String> message) {
        MessageHeaders headers = message.getHeaders();

        @SuppressWarnings("unchecked")
        Map<String, String> attributes = (Map<String, String>) headers.get("Attributes");

        Object messageId = headers.get("id");
        return SqsEntity.builder()
                .body(message.getPayload())
                .headers(headers)
                .messageId(messageId != null ? messageId.toString() : null)
                .receiptHandle((String) headers.get("Sqs_ReceiptHandle"))
                .queueUrl((String) headers.get("Sqs_QueueUrl"))
                .queueName((String) headers.get("Sqs_QueueName"))
                .senderId(attributes != null ? attributes.get("SenderId") : null)
                .approximateReceiveCount(attributes != null ? Integer.parseInt(attributes.get("ApproximateReceiveCount")) : 0)
                .sentTimestamp(attributes != null ? Long.parseLong(attributes.get("SentTimestamp")) : 0L)
                .approximateFirstReceiveTimestamp(attributes != null ? Long.parseLong(attributes.get("ApproximateFirstReceiveTimestamp")) : 0L)
                .timestamp(headers.getTimestamp() != null ? headers.getTimestamp() : 0L)
                .build();
    }
}
