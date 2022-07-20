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
        private Long boundingBoxId;
        private List<Integer> x;
        private List<Integer> y;

        public static OCRBoundingBox of(com.devnus.belloga.labeling.data.domain.OCRBoundingBox ocrBoundingBox) {
            return OCRBoundingBox.builder()
                    .boundingBoxId(ocrBoundingBox.getId())
                    .x(ocrBoundingBox.getRectanglePoint().getAllPointXByList())
                    .y(ocrBoundingBox.getRectanglePoint().getAllPointYByList())
                    .build();
        }
    }
    @Builder
    @Data
    public static class RequestTargetData {
        private DataType dataType;
        private Long ocrDataId;
        private String imageUrl;
        private Boolean isLabeled;

        private List<OCRBoundingBox> boundingBox;

        public static RequestTargetData of(OCRData ocrData) {
            List<OCRBoundingBox> boundingBoxList = new ArrayList<>();
            for(com.devnus.belloga.labeling.data.domain.OCRBoundingBox ocrBoundingBox : ocrData.getBoundingBoxList()) {
                boundingBoxList.add(OCRBoundingBox.of(ocrBoundingBox));
            }

            return RequestTargetData.builder()
                    .dataType(DataType.OCR)
                    .ocrDataId(ocrData.getId())
                    .imageUrl(ocrData.getImageUrl())
                    .isLabeled(ocrData.isLabeled())
                    .boundingBox(boundingBoxList)
                    .build();
        }
    }
}
