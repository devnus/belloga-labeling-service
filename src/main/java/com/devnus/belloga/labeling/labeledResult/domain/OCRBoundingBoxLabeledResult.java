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

    @Column(name = "enterprise_id")
    private String enterpriseId; // 라벨링 완료 기록 이벤트에서 가져옴

    @Column(name = "ocr_bounding_box_id")
    private Long ocrBoundingBoxId;

    @Column(name = "total_labeler_num")
    private Integer totalLabelerNum;

    @Column(name = "accuracy")
    private Float accuracy;

    @Column(name = "text_label")
    private String textLabel;

    @Builder
    public OCRBoundingBoxLabeledResult(Long ocrBoundingBoxId, String enterpriseId, Integer totalLabelerNum, Float accuracy, String textLabel) {
        this.enterpriseId = enterpriseId;
        this.ocrBoundingBoxId = ocrBoundingBoxId;
        this.totalLabelerNum = totalLabelerNum;
        this.accuracy = accuracy;
        this.textLabel = textLabel;
    }
}
