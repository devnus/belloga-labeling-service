package com.devnus.belloga.labeling.labeledResult.service;

import com.devnus.belloga.labeling.common.exception.error.NotFoundDataException;
import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import com.devnus.belloga.labeling.data.repository.OCRBoundingBoxRepository;
import com.devnus.belloga.labeling.labeledResult.domain.OCRBoundingBoxLabeledResult;
import com.devnus.belloga.labeling.labeledResult.dto.ResponseLabeledResult;
import com.devnus.belloga.labeling.labeledResult.repository.OCRBoundingBoxLabeledResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OCRBoundingBoxLabeledResultServiceImpl implements OCRBoundingBoxLabeledResultService {
    private final OCRBoundingBoxLabeledResultRepository ocrBoundingBoxLabeledResultRepository;
    private final OCRBoundingBoxRepository ocrBoundingBoxRepository;

    /**
     * 검증된 라벨 결과를 기록한다.
     * @param enterpriseId
     * @param boundingBoxId
     * @param textLabel
     * @param totalLabelerNum
     * @param reliability
     */
    @Transactional
    @Override
    public void recordLabeledResult(Long projectId, String enterpriseId, Long boundingBoxId, String textLabel, Long totalLabelerNum, Double reliability) {
        ocrBoundingBoxLabeledResultRepository.save(OCRBoundingBoxLabeledResult.builder()
                        .projectId(projectId)
                        .enterpriseId(enterpriseId)
                        .ocrBoundingBoxId(boundingBoxId)
                        .textLabel(textLabel)
                        .totalLabelerNum(totalLabelerNum)
                        .reliability(reliability)
                .build());
    }

    /**
     * 검증된 OCR 데이터 및 신뢰도 조회한다.
     * @param pageable
     * @param enterpriseId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public Page<ResponseLabeledResult.OCRBoundingBoxVerificationResult> getOCRBoundingBoxLabeledResult(Pageable pageable, String enterpriseId, Long projectId) {
        // pageable 해서 가져온다.
        Page<OCRBoundingBoxLabeledResult> list = ocrBoundingBoxLabeledResultRepository.findByEnterpriseIdAndProjectId(pageable, enterpriseId, projectId);

        // mapping 수행, aggregate 가 달라서 of가 아니라 서비스 단에서 해주어야 할듯
        // 최적화 무조건 필요 논의해야함, 일단 급해서 이렇게 처리
        Page<ResponseLabeledResult.OCRBoundingBoxVerificationResult> results = list.map((OCRBoundingBoxLabeledResult ocrBoundingBoxLabeledResult)->{
            OCRBoundingBox ocrBoundingBox = ocrBoundingBoxRepository.findById(ocrBoundingBoxLabeledResult.getOcrBoundingBoxId())
                    .orElseThrow(()->new NotFoundDataException());

            return ResponseLabeledResult.OCRBoundingBoxVerificationResult.builder()
                    .imageUrl(ocrBoundingBox.getOcrData().getImageUrl())
                    .x(ocrBoundingBox.getRectanglePoint().getAllPointXByList())
                    .y(ocrBoundingBox.getRectanglePoint().getAllPointYByList())
                    .reliability(ocrBoundingBoxLabeledResult.getReliability())
                    .textLabel(ocrBoundingBoxLabeledResult.getTextLabel())
                    .totalLabelerNum(ocrBoundingBoxLabeledResult.getTotalLabelerNum())
                    .build();
        });
        // dto 로 mapping 해서 보낸다.
        return results;
    }
}
