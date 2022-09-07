package com.devnus.belloga.labeling.labeledResult.event;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.labeledResult.dto.EventData;
import com.devnus.belloga.labeling.labeledResult.service.OCRBoundingBoxLabeledResultService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class LabeledResultConsumer {
    private final OCRBoundingBoxLabeledResultService ocrBoundingBoxLabeledResultService;

    /**
     * 검증된 라벨링 결과에 대해 기록처리
     * @param eventResult
     * @throws IOException
     */
    @KafkaListener(topics = "record-verification-result", groupId = "record-verification-result-1", containerFactory = "eventDataRecordVerityOCRLabeledResultListener")
    protected void consumeRecordVerificationResult(EventData.RecordVerityOCRLabeledResult eventResult) throws IOException {
        // 컨슘받아 기록한다.

        if(eventResult.getDataType().equals(DataType.OCR)) {
            // OCR인 경우
            ocrBoundingBoxLabeledResultService.recordLabeledResult(
                    eventResult.getProjectId(),
                    eventResult.getEnterpriseId(),
                    eventResult.getBoundingBoxId(),
                    eventResult.getTextLabel(),
                    eventResult.getTotalLabelerNum(),
                    eventResult.getReliability());
        }
    }
}
