package com.devnus.belloga.labeling.data.service;

import com.devnus.belloga.labeling.data.dto.ResponseOCRData;

import java.util.Optional;

public interface OCRDataService {
    Optional<ResponseOCRData.TargetData> getRandomTargetOCRData();
}
