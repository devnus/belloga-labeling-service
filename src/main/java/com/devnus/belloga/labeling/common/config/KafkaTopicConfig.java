package com.devnus.belloga.labeling.common.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {
    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;

    @Value(value = "${app.topic.labeled-data.pay-tmp-point-to-labeler}")
    private String PAY_TMP_POINT_TO_LABELER;

    @Value(value = "${app.topic.labeled-data.labeling-ocr-bounding-box}")
    private String LABELING_OCR_BOUNDING_BOX;

    @Value(value = "${app.topic.labeled-data.change-tmp-point-to-point}")
    private String CHANGE_TMP_POINT_TO_POINT;

    @Value(value = "${app.topic.labeled-data.delete-tmp-point}")
    private String DELETE_TMP_POINT;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        return new KafkaAdmin(configs);
    }

    /**
     * 라벨링 수행 후 포인트 지급 토픽 생성
     * 같은 이름의 토픽이 등록되어 있다면 동작하지 않음
     * partition 개수, replica 개수는 논의 후 수정
     */
    @Bean
    public NewTopic createPayToLabelerTopic() {
        return TopicBuilder.name(PAY_TMP_POINT_TO_LABELER)
                .partitions(1)
                .replicas(1)
                .build();
    }

    /**
     * OCR 바운딩박스 라벨링 수행 후 이벤트를 검증 마이크로서비스에 전달을 목적
     */
    @Bean
    public NewTopic createLabelingOCRBoundingBoxEventTopic() {
        return TopicBuilder.name(LABELING_OCR_BOUNDING_BOX)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic changeTmpPointToPointEventTopic() {
        return TopicBuilder.name(CHANGE_TMP_POINT_TO_POINT)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic deleteTmpPointEventTopic() {
        return TopicBuilder.name(DELETE_TMP_POINT)
                .partitions(1)
                .replicas(1)
                .build();
    }
}

