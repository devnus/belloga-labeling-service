package com.devnus.belloga.labeling.labeledData.repository;

import com.devnus.belloga.labeling.labeledData.domain.LabeledOCRData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LabeledOCRDataRepository extends JpaRepository<LabeledOCRData, Long> {
    Optional<LabeledOCRData> findByLabelingUUID(String labelingUUID);
}
