package com.devnus.belloga.labeling.data.event;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.dto.EventPreprocessing;
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
    @KafkaListener(topics = "success-verify-text-label", groupId = "success-verify-text-label-1", containerFactory = "eventVerificationSuccessVerifyTextLabelListener")
    protected void consumeSuccessVerifyOCRLabelingEvent(EventVerification.SuccessVerifyTextLabel event) throws IOException {
        if(event.getDataType().equals(DataType.OCR)) {
            // 데이터 타입이 OCR 인 경우
            // 라벨링 상태 성공으로 변경
            ocrDataService.recordSuccessOCRLabelingResult(
                    event.getBoundingBoxId(),
                    event.getTotalLabelerNum(),
                    event.getReliability(),
                    event.getTextLabel());
        }
    }

    /**
     * 전처리된 OCR 데이터를 라벨링 수행 위해 라벨링 마이크로서비스로 업로드한다.
     * @param event
     * @throws IOException
     */
    @KafkaListener(topics = "ocr-data-preprocessing", groupId = "ocr-data-preprocessing-1", containerFactory = "eventPreprocessingOCRPreprocessingDataListener")
    protected void consumeOCRPreprocessingData(EventPreprocessing.OCRPreprocessingData event) throws IOException {
        ocrDataService.uploadPreprocessingData(event.getProjectId(), event.getEnterpriseId(),
                event.getRawDataId(),
                event.getImageUrl(),
                event.getBoundingBoxInfo());
    }


}
