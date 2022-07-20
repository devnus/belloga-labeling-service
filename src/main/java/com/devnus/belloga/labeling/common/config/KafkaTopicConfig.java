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
    public NewTopic registerCustomAccountEnterpriseTopic() {
        return TopicBuilder.name(PAY_TMP_POINT_TO_LABELER)
                .partitions(1)
                .replicas(1)
                .compact()
                .build();
    }
}

