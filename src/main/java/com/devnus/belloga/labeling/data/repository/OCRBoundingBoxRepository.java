package com.devnus.belloga.labeling.data.repository;

import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OCRBoundingBoxRepository extends JpaRepository<OCRBoundingBox, Long> {
}
