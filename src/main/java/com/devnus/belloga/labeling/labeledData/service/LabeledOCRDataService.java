package com.devnus.belloga.labeling.labeledData.service;

public interface LabeledOCRDataService {
    void labelingBoundingBox(String labelerId, Long boundingBoxId, String textLabel);
}
