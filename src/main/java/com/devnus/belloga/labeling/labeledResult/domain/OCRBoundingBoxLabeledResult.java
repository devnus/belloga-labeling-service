package com.devnus.belloga.labeling.labeledResult.domain;

import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * OCR 바운딩박스 라벨링 결과
 */
@Entity
@Table(name = "ocr_bounding_box_labeled_result")
@Getter
@NoArgsConstructor
public class OCRBoundingBoxLabeledResult {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ocr_bounding_box_id")
    private Long ocrBoundingBoxId;

    @Column(name = "verification_labeling_count")
    private Integer verificationLabelingCount;

    @Column(name = "accuracy")
    private Float accuracy;

    @Column(name = "text_label")
    private String textLabel;

    @Builder
    public OCRBoundingBoxLabeledResult(Long ocrBoundingBoxId, Integer verificationLabelingCount, Float accuracy, String textLabel) {
        this.ocrBoundingBoxId = ocrBoundingBoxId;
        this.verificationLabelingCount = verificationLabelingCount;
        this.accuracy = accuracy;
        this.textLabel = textLabel;
    }
}
