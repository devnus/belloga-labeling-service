package com.devnus.belloga.labeling.labeledResult.repository;

import com.devnus.belloga.labeling.labeledResult.domain.OCRBoundingBoxLabeledResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OCRBoundingBoxLabeledResultRepository extends JpaRepository<OCRBoundingBoxLabeledResult, Long> {
    Page<OCRBoundingBoxLabeledResult> findByEnterpriseIdAndProjectId(Pageable pageable, String enterpriseId, Long projectId);
}
