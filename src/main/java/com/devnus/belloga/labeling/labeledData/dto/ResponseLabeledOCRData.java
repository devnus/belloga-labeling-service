package com.devnus.belloga.labeling.labeledData.dto;

import com.devnus.belloga.labeling.labeledData.domain.LabeledOCRData;
import com.devnus.belloga.labeling.labeledData.domain.LabelingVerificationStatus;
import lombok.Builder;
import lombok.Data;

public class ResponseLabeledOCRData {

    @Builder
    @Data
    public static class LabeledOCRDataInfo {
        private String labelingUUID;
        private LabelingVerificationStatus labelingVerificationStatus;
        private String textLabel;

        public static LabeledOCRDataInfo of(LabeledOCRData data) {
            //entity to dto
            return LabeledOCRDataInfo.builder()
                    .labelingUUID(data.getLabelingUUID())
                    .labelingVerificationStatus(data.getLabelingVerificationStatus())
                    .textLabel(data.getTextLabel())
                    .build();
        }
    }
}
