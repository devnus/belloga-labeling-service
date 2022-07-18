package com.devnus.belloga.labeling.labeledData.event;

import com.devnus.belloga.labeling.labeledData.dto.EventLabeledData;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * LabeledData Aggregate의 카프카 프로듀서
 */
@Component
@RequiredArgsConstructor
public class LabeledDataProducer {
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @Value(value = "${app.topic.labeled-data.pay-tmp-point-to-labeler}")
    private String PAY_TMP_POINT_TO_LABELER;

    public void payTmpPointToLabeler(String labelerId, Long value) {
        kafkaTemplate.send(PAY_TMP_POINT_TO_LABELER, new EventLabeledData.PayTmpPointToLabeler(labelerId, value));
    }
}
