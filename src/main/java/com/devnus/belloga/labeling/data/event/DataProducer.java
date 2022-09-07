package com.devnus.belloga.labeling.data.event;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.dto.EventData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value(value = "${app.topic.data.record-verification-result}")
    private String RECORD_VERIFICATION_RESULT;

    /**
     * LabeledResult 쪽으로 OCR 검증된 라벨링을 보낸다.
     */
    public void recordOCRVerificationResult(Long projectId, String enterpriseId, Long boundingBoxId, Long totalLabelerNum, Double reliability, String textLabel) {
        kafkaTemplate.send(RECORD_VERIFICATION_RESULT, new EventData.RecordVerityOCRLabeledResult(projectId, DataType.OCR, enterpriseId, boundingBoxId, totalLabelerNum, reliability, textLabel));
    }
}
