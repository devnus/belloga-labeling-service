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
    @Value(value = "${app.topic.labeled-data.labeling-ocr-bounding-box}")
    private String LABELING_OCR_BOUNDING_BOX;

    public void payTmpPointToLabeler(String labelerId, String labelingUUID, Long value) {
        // 라벨링 UUID를 포함하여 point 마이크로서비스로 보낸다. // 그래야 OCR 외 다른 유형의 데이터도 포인트 지급 처리 가능
        kafkaTemplate.send(PAY_TMP_POINT_TO_LABELER, new EventLabeledData.PayTmpPointToLabeler(labelerId, labelingUUID, value));
    }

    public void producingOCRBoundingBoxLabelingEvent(Long ocrBoundingBoxId, String textLabel, String labelingUUID) {
        kafkaTemplate.send(LABELING_OCR_BOUNDING_BOX, new EventLabeledData.LabelingOCRBoundingBox(ocrBoundingBoxId, textLabel, labelingUUID));
    }
}
