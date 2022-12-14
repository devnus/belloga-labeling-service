package com.devnus.belloga.labeling.common.config;

import com.devnus.belloga.labeling.data.dto.EventPreprocessing;
import com.devnus.belloga.labeling.labeledData.dto.EventVerification;
import com.devnus.belloga.labeling.labeledResult.dto.EventData;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {
    @Value("${spring.kafka.bootstrap-servers}")
    private String BOOTSTRAP_SERVERS;
    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String AUTO_OFFSET_RESET;
    @Value("${spring.kafka.consumer.enable-auto-commit}")
    private boolean AUTO_COMMIT;

    private Map<String, Object> configProps() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class); //카프카에서 받은 키 역 직렬화
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class); //카프카에서 받은 값 역 직렬화
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, AUTO_OFFSET_RESET);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, AUTO_COMMIT);
        return props;
    }

    @Bean
    ConsumerFactory<String,Object> consumerFactory(){
        JsonDeserializer<Object> deserializer = new JsonDeserializer<>(Object.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Object> containerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
    @Bean
    ConsumerFactory<String,EventPreprocessing.OCRPreprocessingData> eventPreprocessingOCRPreprocessingDataFactory(){
        JsonDeserializer<EventPreprocessing.OCRPreprocessingData> deserializer = new JsonDeserializer<>(EventPreprocessing.OCRPreprocessingData.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventPreprocessing.OCRPreprocessingData> eventPreprocessingOCRPreprocessingDataListener(){
        ConcurrentKafkaListenerContainerFactory<String, EventPreprocessing.OCRPreprocessingData> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventPreprocessingOCRPreprocessingDataFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, com.devnus.belloga.labeling.data.dto.EventVerification.SuccessVerifyTextLabel> eventVerificationSuccessVerifyTextLabelFactory(){
        JsonDeserializer<com.devnus.belloga.labeling.data.dto.EventVerification.SuccessVerifyTextLabel> deserializer = new JsonDeserializer<>(com.devnus.belloga.labeling.data.dto.EventVerification.SuccessVerifyTextLabel.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, com.devnus.belloga.labeling.data.dto.EventVerification.SuccessVerifyTextLabel> eventVerificationSuccessVerifyTextLabelListener(){
        ConcurrentKafkaListenerContainerFactory<String, com.devnus.belloga.labeling.data.dto.EventVerification.SuccessVerifyTextLabel> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventVerificationSuccessVerifyTextLabelFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, EventVerification.SuccessVerifyLabeling> eventVerificationSuccessVerifyTextLabelLabeledDataFactory(){
        JsonDeserializer<EventVerification.SuccessVerifyLabeling> deserializer = new JsonDeserializer<>(EventVerification.SuccessVerifyLabeling.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventVerification.SuccessVerifyLabeling> eventVerificationSuccessVerifyTextLabelLabeledDataListener(){
        ConcurrentKafkaListenerContainerFactory<String, EventVerification.SuccessVerifyLabeling> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventVerificationSuccessVerifyTextLabelLabeledDataFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, EventVerification.FailVerifyLabeling> eventVerificationFailVerifyTextLabelLabeledDataFactory(){
        JsonDeserializer<EventVerification.FailVerifyLabeling> deserializer = new JsonDeserializer<>(EventVerification.FailVerifyLabeling.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventVerification.FailVerifyLabeling> eventVerificationFailVerifyTextLabelLabeledDataListener(){
        ConcurrentKafkaListenerContainerFactory<String, EventVerification.FailVerifyLabeling> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventVerificationFailVerifyTextLabelLabeledDataFactory());
        return factory;
    }

    @Bean
    ConsumerFactory<String, EventData.RecordVerityOCRLabeledResult> eventDataRecordVerityOCRLabeledResultFactory(){
        JsonDeserializer<EventData.RecordVerityOCRLabeledResult> deserializer = new JsonDeserializer<>(EventData.RecordVerityOCRLabeledResult.class);

        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);
        return new DefaultKafkaConsumerFactory<>(configProps(), new StringDeserializer(), deserializer);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventData.RecordVerityOCRLabeledResult> eventDataRecordVerityOCRLabeledResultListener(){
        ConcurrentKafkaListenerContainerFactory<String, EventData.RecordVerityOCRLabeledResult> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(eventDataRecordVerityOCRLabeledResultFactory());
        return factory;
    }
}

