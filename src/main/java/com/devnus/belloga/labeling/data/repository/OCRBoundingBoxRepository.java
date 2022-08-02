package com.devnus.belloga.labeling.data.repository;

import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OCRBoundingBoxRepository extends JpaRepository<OCRBoundingBox, Long> {
    long countByIsLabeled(boolean isLabeled);
    Page<OCRBoundingBox> findAllByIsLabeled(Pageable pageable, boolean isLabeled);
}
