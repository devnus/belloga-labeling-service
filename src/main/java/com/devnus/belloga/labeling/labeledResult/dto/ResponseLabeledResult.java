package com.devnus.belloga.labeling.labeledResult.dto;


import lombok.Builder;
import lombok.Data;

import java.util.List;

public class ResponseLabeledResult {
    @Builder
    @Data
    public static class OCRBoundingBoxVerificationResult {
        private String imageUrl;
        private List<Integer> x;
        private List<Integer> y;
        private String textLabel;
        private Long totalLabelerNum;
        private Double reliability; // 검증 신뢰도
    }
}
