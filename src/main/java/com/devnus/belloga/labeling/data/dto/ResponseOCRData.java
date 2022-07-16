package com.devnus.belloga.labeling.data.dto;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.domain.OCRData;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public class ResponseOCRData {

    @Builder
    @Data
    public static class OCRBoundingBox {
        List<Integer> x;
        List<Integer> y;

        public static OCRBoundingBox of(com.devnus.belloga.labeling.data.domain.OCRBoundingBox ocrBoundingBox) {
            return OCRBoundingBox.builder()
                    .x(ocrBoundingBox.getRectanglePoint().getAllPointXByList())
                    .y(ocrBoundingBox.getRectanglePoint().getAllPointYByList())
                    .build();
        }
    }
    @Builder
    @Data
    public static class TargetData {
        private DataType dataType;
        private Long ocrDataId;
        private String imageUrl;
        private Boolean isLabeled;

        private List<OCRBoundingBox> boundingBox;

        public static TargetData of(OCRData ocrData) {
            List<OCRBoundingBox> boundingBoxList = new ArrayList<>();
            for(com.devnus.belloga.labeling.data.domain.OCRBoundingBox ocrBoundingBox : ocrData.getBoundingBoxList()) {
                boundingBoxList.add(OCRBoundingBox.of(ocrBoundingBox));
            }

            return TargetData.builder()
                    .dataType(DataType.OCR)
                    .ocrDataId(ocrData.getId())
                    .imageUrl(ocrData.getImageUrl())
                    .isLabeled(ocrData.isLabeled())
                    .boundingBox(boundingBoxList)
                    .build();
        }
    }
}
