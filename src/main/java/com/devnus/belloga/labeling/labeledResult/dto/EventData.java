package com.devnus.belloga.labeling.labeledResult.dto;

import com.devnus.belloga.labeling.data.domain.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class EventData {
    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RecordVerityOCRLabeledResult {
        private DataType dataType;
        private String enterpriseId;
        private Long boundingBoxId;
        private Long totalLabelerNum;
        private Double reliability;
        private String textLabel;
    }
}
