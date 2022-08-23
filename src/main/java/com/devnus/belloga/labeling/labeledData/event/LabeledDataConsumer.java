package com.devnus.belloga.labeling.labeledData.event;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.labeledData.dto.EventVerification;
import com.devnus.belloga.labeling.labeledData.service.LabeledOCRDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LabeledDataConsumer {
    private final LabeledOCRDataService labeledOCRDataService;

    /**
     * 라벨링이 통과된 라벨러에게 라벨링 통과됨으로 상태변경 (검증 마이크로서비스로부터 전달)
     * @param event
     * @throws IOException
     */
    @KafkaListener(topics = "success-verify-labeling", groupId = "success-verify-labeling-1", containerFactory = "eventVerificationSuccessVerifyTextLabelLabeledDataListener")
    protected void consumeSuccessLabelerLabeledData(EventVerification.SuccessVerifyLabeling event) throws IOException {
        if(event.getDataType().equals(DataType.OCR)) {
            labeledOCRDataService.changeLabelingVerificationStatusSuccess(event.getLabelingUUID());
        }
        // 다른 유형의 데이터
    }

    /**
     * 라벨링 불통된 라벨러에게 라벨링 불통으로 상태 변경 (검증 마이크로서비스로부터 전달)
     * @param event
     * @throws IOException
     */
    @KafkaListener(topics = "fail-verify-labeling", groupId = "fail-verify-labeling-1", containerFactory = "eventVerificationFailVerifyTextLabelLabeledDataListener")
    protected void consumeFailVerityLabeling(EventVerification.FailVerifyLabeling event) throws IOException {
        if(event.getDataType().equals(DataType.OCR)) {
            labeledOCRDataService.changeLabelingVerificationStatusFail(event.getLabelingUUID());
        }
        // 다른 유형의 데이터
    }
}
