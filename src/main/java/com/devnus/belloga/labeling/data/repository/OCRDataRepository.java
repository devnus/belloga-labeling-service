package com.devnus.belloga.labeling.data.repository;

import com.devnus.belloga.labeling.data.domain.OCRData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OCRDataRepository extends JpaRepository<OCRData, Long> {
}
