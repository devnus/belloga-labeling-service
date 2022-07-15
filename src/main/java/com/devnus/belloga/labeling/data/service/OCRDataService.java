package com.devnus.belloga.labeling.data.service;

import com.devnus.belloga.labeling.data.dto.ResponseData;

import java.util.Optional;

public interface OCRDataService {
    Optional<ResponseData.OCRTargetData> getRandomTargetOCRData();
}
