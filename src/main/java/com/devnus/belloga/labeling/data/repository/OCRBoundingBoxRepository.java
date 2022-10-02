package com.devnus.belloga.labeling.data.repository;

import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OCRBoundingBoxRepository extends JpaRepository<OCRBoundingBox, Long> {
    long countByIsLabeled(boolean isLabeled);
    Page<OCRBoundingBox> findAllByIsLabeled(Pageable pageable, boolean isLabeled);
    @Query("select count(*) from OCRBoundingBox b join b.ocrData d where d.projectId = :projectId")
    Long countByProjectIdAll(@Param("projectId") Long projectId);
    @Query("select count(*) from OCRBoundingBox b join b.ocrData d where d.projectId = :projectId AND b.isLabeled = true")
    Long countByProjectIdIsLabeled(@Param("projectId") Long projectId);

}
