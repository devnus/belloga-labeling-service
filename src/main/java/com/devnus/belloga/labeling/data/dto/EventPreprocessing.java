package com.devnus.belloga.labeling.data.dto;

import com.devnus.belloga.labeling.data.domain.DataType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class EventPreprocessing {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BoundingBox {
        private Integer[] x;
        private Integer[] y;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OCRPreprocessingData {
        private Long projectId;
        private DataType dataType;
        private String enterpriseId;
        private Long rawDataId;
        private String imageUrl;
        private BoundingBox[] boundingBoxInfo;
    }
}
