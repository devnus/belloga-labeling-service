package com.devnus.belloga.labeling.labeledData.service;

import com.devnus.belloga.labeling.labeledData.dto.ResponseLabeledOCRData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface LabeledOCRDataService {
    void labelingBoundingBox(String labelerId, Long boundingBoxId, String textLabel);
    void changeLabelingVerificationStatusSuccess(String labelingUUID);
    void changeLabelingVerificationStatusFail(String labelingUUID);

    Page<ResponseLabeledOCRData.LabeledOCRDataInfo> getMyLabelingInfo(Pageable pageable, String labelerId);
}
