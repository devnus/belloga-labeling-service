package com.devnus.belloga.labeling.labeledData.domain;

import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 바운딩박스에 대해 라벨링 수행된 것 적재
 */
@Entity
@Table(name = "labeled_ocr_data")
@Getter
@NoArgsConstructor
public class LabeledOCRData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "labeler_id")
    private String labelerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ocr_bounding_box_id") // 연관관계의 주인
    private OCRBoundingBox ocrBoundingBox;

    @Column(name = "text_label")
    private String textLabel;

    @Builder
    public LabeledOCRData(String labelerId, OCRBoundingBox ocrBoundingBox, String textLabel) {
        this.labelerId = labelerId;
        this.ocrBoundingBox = ocrBoundingBox;
        this.textLabel = textLabel;
    }

}
