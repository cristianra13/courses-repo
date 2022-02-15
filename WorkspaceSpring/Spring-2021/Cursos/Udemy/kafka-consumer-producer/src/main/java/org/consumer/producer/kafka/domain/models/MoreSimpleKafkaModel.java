package org.consumer.producer.kafka.domain.models;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class MoreSimpleKafkaModel {
    private String title;
    private String description;
}
