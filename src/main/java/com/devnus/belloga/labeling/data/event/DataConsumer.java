package com.devnus.belloga.labeling.data.event;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.dto.EventVerification;
import com.devnus.belloga.labeling.data.service.OCRDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
@RequiredArgsConstructor
public class DataConsumer {
    private final OCRDataService ocrDataService;
    /**
     * 데이터 검증 마이크로서비스로부터 OCR 검증 성공 시 데이터 consume
     * 라벨링 완료를 기록한다.
     * @param event
     * @return
     * @throws IOException
     */
    @KafkaListener(topics = "success-verify-text-label", groupId = "success-verify-text-label-1")
    protected void consumeSuccessVerifyOCRLabelingEvent(Object event) throws IOException {
        EventVerification.SuccessVerifyTextLabel eventData = (EventVerification.SuccessVerifyTextLabel) event;
        if(eventData.getDataType().equals(DataType.OCR)) {
            // 데이터 타입이 OCR 인 경우
            // 라벨링 상태 성공으로 변경
            ocrDataService.recordSuccessOCRLabelingResult(
                    eventData.getBoundingBoxId(),
                    eventData.getTotalLabelerNum(),
                    eventData.getReliability(),
                    eventData.getTextLabel());
        }
    }

    @KafkaListener(topics = "data-preprocessing", groupId = "data-preprocessing-1")
    protected void consumePreprocessingData(Object event) throws IOException {

    }


}
