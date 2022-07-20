package com.devnus.belloga.labeling.labeledData.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

public class RequestLabeledOCRData {

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegisterLabeling {
        @NotNull(message = "바운딩박스 아이디가 없습니다.")
        private Long boundingBoxId;
        @NotNull(message = "레이블링이 되지 않았습니다.")
        private String label;
    }
}
