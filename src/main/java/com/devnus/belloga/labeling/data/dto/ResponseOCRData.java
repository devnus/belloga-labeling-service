package com.devnus.belloga.labeling.data.dto;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.domain.OCRData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ResponseOCRData {

    @Builder
    @Data
    public static class OCRBoundingBox {
        private DataType dataType;
        private Long ocrDataId;
        private String imageUrl;

        private Long boundingBoxId;
        private List<Integer> x;
        private List<Integer> y;

        public static OCRBoundingBox of(com.devnus.belloga.labeling.data.domain.OCRBoundingBox ocrBoundingBox) {
            return OCRBoundingBox.builder()
                    .dataType(DataType.OCR)
                    .ocrDataId(ocrBoundingBox.getOcrData().getId())
                    .imageUrl(ocrBoundingBox.getOcrData().getImageUrl())
                    .boundingBoxId(ocrBoundingBox.getId())
                    .x(ocrBoundingBox.getRectanglePoint().getAllPointXByList())
                    .y(ocrBoundingBox.getRectanglePoint().getAllPointYByList())
                    .build();
        }
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgressRate{
        private Double progressRate;
    }
}
