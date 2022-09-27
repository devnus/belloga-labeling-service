package com.devnus.belloga.labeling.labeledResult.domain;

import com.devnus.belloga.labeling.common.domain.BaseTimeEntity;
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
public class OCRBoundingBoxLabeledResult extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "enterprise_id")
    private String enterpriseId; // 라벨링 완료 기록 이벤트에서 가져옴

    @Column(name = "project_id")
    private Long projectId;

    @Column(name = "ocr_bounding_box_id")
    private Long ocrBoundingBoxId;

    @Column(name = "total_labeler_num")
    private Long totalLabelerNum;

    @Column(name = "reliability")
    private Double reliability; // 검증 신뢰도

    @Column(name = "text_label")
    private String textLabel;

    @Builder
    public OCRBoundingBoxLabeledResult(Long projectId, Long ocrBoundingBoxId, String enterpriseId, Long totalLabelerNum, Double reliability, String textLabel) {
        this.projectId = projectId;
        this.enterpriseId = enterpriseId;
        this.ocrBoundingBoxId = ocrBoundingBoxId;
        this.totalLabelerNum = totalLabelerNum;
        this.reliability = reliability;
        this.textLabel = textLabel;
    }
}
