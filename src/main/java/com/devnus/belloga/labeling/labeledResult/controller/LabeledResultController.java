package com.devnus.belloga.labeling.labeledResult.controller;

import com.devnus.belloga.labeling.common.aop.annotation.GetAccountIdentification;
import com.devnus.belloga.labeling.common.aop.annotation.UserRole;
import com.devnus.belloga.labeling.common.dto.CommonResponse;
import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.labeledResult.service.OCRBoundingBoxLabeledResultService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/labeled-result")
public class LabeledResultController {
    private final OCRBoundingBoxLabeledResultService ocrBoundingBoxLabeledResultService;

    public LabeledResultController(OCRBoundingBoxLabeledResultService ocrBoundingBoxLabeledResultService) {
        this.ocrBoundingBoxLabeledResultService = ocrBoundingBoxLabeledResultService;
    }

    /**
     * 검증된 라벨링 결과 및 신뢰도를 조회한다.
     * @param type
     * @return
     */
    @GetMapping("/v1/verification/results/{type}/{projectId}")
    public ResponseEntity<CommonResponse> getVerificationLabeledResult(
            @GetAccountIdentification(role = UserRole.ENTERPRISE) String enterpriseId,
            @PathVariable("type") DataType type,
            @PathVariable("projectId") Long projectId,
            Pageable pageable) {

        Object result = null;
        if(type.equals(DataType.OCR)) {
            // OCR 바운딩 박스 검증 데이터 조회
            result = ocrBoundingBoxLabeledResultService.getOCRBoundingBoxLabeledResult(pageable, enterpriseId, projectId);
        }
        return new ResponseEntity<>(CommonResponse.builder()
                .success(true)
                .response(result)
                .build(), HttpStatus.OK);
    }
}
