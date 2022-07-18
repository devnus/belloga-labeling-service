package com.devnus.belloga.labeling.labeledData.controller;

import com.devnus.belloga.labeling.common.aop.annotation.GetAccountIdentification;
import com.devnus.belloga.labeling.common.aop.annotation.UserRole;
import com.devnus.belloga.labeling.common.dto.CommonResponse;
import com.devnus.belloga.labeling.labeledData.dto.RequestLabeledOCRData;
import com.devnus.belloga.labeling.labeledData.service.LabeledOCRDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * OCR 데이터의 레이블링 관련 컨트롤러이다.
 */
@RestController
@RequestMapping("/api")
public class LabeledOCRDataController {
    private final LabeledOCRDataService labeledOCRDataService;

    public LabeledOCRDataController(LabeledOCRDataService labeledOCRDataService) {
        this.labeledOCRDataService = labeledOCRDataService;
    }

    /**
     * OCR 이미지 바운딩박스에 대해 라벨링을 수행한다.
     * @param labelerId
     * @param registerLabelingDto
     * @return CommonResponse
     */
    @PostMapping("/v1/ocr-data/labeling")
    public ResponseEntity<CommonResponse> labelingOCRData(@GetAccountIdentification(role = UserRole.LABELER) String labelerId,
                                                          @Valid @RequestBody RequestLabeledOCRData.RegisterLabeling registerLabelingDto) {

        labeledOCRDataService.labelingBoundingBox(labelerId, registerLabelingDto.getBoundingBoxId(), registerLabelingDto.getLabel());

        return new ResponseEntity<>(CommonResponse.builder()
                .success(true)
                .build(), HttpStatus.OK);
    }
}
