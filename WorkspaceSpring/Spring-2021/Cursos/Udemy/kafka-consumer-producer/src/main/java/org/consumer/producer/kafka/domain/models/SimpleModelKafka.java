package org.consumer.producer.kafka.domain.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class SimpleModelKafka {
    private String fieldOne;
    private String fieldTwo;
}
