package com.devnus.belloga.labeling.data.domain;

import com.devnus.belloga.labeling.common.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * OCR 데이터를 전처리하고 바운딩박스를 가진 OCR 데이터 대한 엔티티이다.
 */
@Entity
@Table(name = "ocr_bounding_box")
@Getter
@NoArgsConstructor
public class OCRBoundingBox extends BaseTimeEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ocr_data_id") // 연관관계의 주인
    private OCRData ocrData;

    @Column(name = "ocr_annotation_type")
    @Enumerated(EnumType.STRING)
    private OCRAnnotationType annotationType;

    @Embedded
    private RectanglePoint rectanglePoint; // 바운딩박스는 사각형 포인트를 가짐

    @Column(name = "is_labeled")
    private boolean isLabeled;

    @Builder
    public OCRBoundingBox(OCRAnnotationType annotationType, RectanglePoint rectanglePoint) {
        this.annotationType = annotationType;
        this.rectanglePoint = rectanglePoint;
        isLabeled = false;
    }

    public void setOcrData(OCRData ocrData) {
        this.ocrData = ocrData;
    }
    public void changeLabeledStatus(boolean isLabeled) {
        this.isLabeled = isLabeled;
    }
}
