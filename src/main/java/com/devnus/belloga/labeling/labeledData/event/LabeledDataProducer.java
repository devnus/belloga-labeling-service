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

    @Value(value = "${app.topic.labeled-data.change-tmp-point-to-point}")
    private String CHANGE_TMP_POINT_TO_POINT;

    @Value(value = "${app.topic.labeled-data.delete-tmp-point}")
    private String DELETE_TMP_POINT;

    public void payTmpPointToLabeler(String labelerId, String labelingUUID, Long value) {
        // 라벨링 UUID를 포함하여 point 마이크로서비스로 보낸다. // 그래야 OCR 외 다른 유형의 데이터도 포인트 지급 처리 가능
        kafkaTemplate.send(PAY_TMP_POINT_TO_LABELER, new EventLabeledData.PayTmpPointToLabeler(labelerId, labelingUUID, value));
    }

    public void producingOCRBoundingBoxLabelingEvent(Long ocrBoundingBoxId, String textLabel, String labelingUUID) {
        kafkaTemplate.send(LABELING_OCR_BOUNDING_BOX, new EventLabeledData.LabelingOCRBoundingBox(ocrBoundingBoxId, textLabel, labelingUUID));
    }

    /**
     * labelingUUID를 전달해 임시포인트를 포인트로 변환하는 이벤트 발행
     * @param labelingUUID
     */
    public void changeTmpPointToPoint(String labelingUUID) {
        kafkaTemplate.send(CHANGE_TMP_POINT_TO_POINT, new EventLabeledData.ChangeTmpPointToPoint(labelingUUID));
    }

    /**
     * labelingUUID를 담아 임시 포인트를 삭제하는 이벤트 발행
     * @param labelingUUID
     */
    public void deleteTmpPoint(String labelingUUID) {
        kafkaTemplate.send(DELETE_TMP_POINT, new EventLabeledData.DeleteTmpPoint(labelingUUID));
    }
}
