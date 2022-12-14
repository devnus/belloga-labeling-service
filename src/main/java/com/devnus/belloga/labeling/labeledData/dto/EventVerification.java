package com.devnus.belloga.labeling.labeledData.dto;

import com.devnus.belloga.labeling.data.domain.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventVerification {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FailVerifyLabeling {
        private DataType dataType;
        private String labelingUUID;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SuccessVerifyLabeling {
        private DataType dataType;
        private String labelingUUID;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SuccessVerifyTextLabel {
        private DataType dataType;
        private Long boundingBoxId;
        private Long totalLabelerNum;
        private Double reliability;
        private String textLabel;
    }
}
