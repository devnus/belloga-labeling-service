package com.devnus.belloga.labeling.labeledData.controller;

import com.devnus.belloga.labeling.common.aop.annotation.GetAccountIdentification;
import com.devnus.belloga.labeling.common.aop.annotation.UserRole;
import com.devnus.belloga.labeling.common.dto.CommonResponse;
import com.devnus.belloga.labeling.labeledData.dto.RequestLabeledOCRData;
import com.devnus.belloga.labeling.labeledData.service.LabeledOCRDataService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * OCR 데이터의 레이블링 관련 컨트롤러이다.
 */
@RestController
@RequestMapping("/api/labeled-data")
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
    @PostMapping("/v1/ocr-data")
    public ResponseEntity<CommonResponse> labelingOCRData(@GetAccountIdentification(role = UserRole.LABELER) String labelerId,
                                                          @Valid @RequestBody RequestLabeledOCRData.RegisterLabeling registerLabelingDto) {

        labeledOCRDataService.labelingBoundingBox(labelerId, registerLabelingDto.getBoundingBoxId(), registerLabelingDto.getLabel());

        return new ResponseEntity<>(CommonResponse.builder()
                .success(true)
                .build(), HttpStatus.OK);
    }
    /**
     * 자신이 수행한 라벨링 조회, 테스트코드는 나중에 작성하자
     * @param labelerId
     * @return CommonResponse
     */
    @GetMapping("/v1/ocr-data")
    public ResponseEntity<CommonResponse> getMyLabelingOCRData(@GetAccountIdentification(role = UserRole.LABELER) String labelerId, Pageable pageable) {
        return new ResponseEntity<>(CommonResponse.builder()
                .success(true)
                .response(labeledOCRDataService.getMyLabelingInfo(pageable, labelerId))
                .build(), HttpStatus.OK);
    }
}
