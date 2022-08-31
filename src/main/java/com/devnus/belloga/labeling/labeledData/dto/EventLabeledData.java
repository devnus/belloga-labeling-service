package com.devnus.belloga.labeling.labeledData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventLabeledData {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PayTmpPointToLabeler {
        private String labelerId;
        private String labelingUUID; // 포인트 지급 처리용 라벨링 고유값
        private Long value;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LabelingOCRBoundingBox {
        private Long ocrBoundingBoxId; // 라벨링 대상 바운딩박스 ID값
        private String textLabel;
        private String labelingUUID;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChangeTmpPointToPoint {
        private String labelingUUID;
    }
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DeleteTmpPoint {
        private String labelingUUID;
    }
}
