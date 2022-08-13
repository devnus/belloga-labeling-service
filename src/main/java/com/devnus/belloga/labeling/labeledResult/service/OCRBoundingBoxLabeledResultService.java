package com.devnus.belloga.labeling.labeledResult.service;

import com.devnus.belloga.labeling.labeledResult.dto.ResponseLabeledResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OCRBoundingBoxLabeledResultService {
    void recordLabeledResult(String enterpriseId, Long boundingBoxId, String textLabel, Long totalLabelerNum, Double reliability);
    Page<ResponseLabeledResult.OCRBoundingBoxVerificationResult> getOCRBoundingBoxLabeledResult(Pageable pageable, String enterpriseId);
}

