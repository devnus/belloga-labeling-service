package com.devnus.belloga.labeling.data.service;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.dto.EventPreprocessing;
import com.devnus.belloga.labeling.data.dto.ResponseOCRData;

import java.util.List;
import java.util.Optional;

public interface OCRDataService {
    Optional<ResponseOCRData.OCRBoundingBox> getRandomTargetOCRData();
    void recordSuccessOCRLabelingResult(Long boundingBoxId, Long totalLabelerNum, Double accuracy, String textLabel);
    void uploadPreprocessingData(String enterpriseId, Long rawDataId, String imageUrl, EventPreprocessing.BoundingBox[] boundingBoxList);
}
