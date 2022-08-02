package com.devnus.belloga.labeling.data.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * OCR 데이터를 위한 엔티티
 */
@Entity
@Table(name = "ocr_data")
@Getter
@NoArgsConstructor
public class OCRData {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "raw_data_id")
    private Long rawDataId;

    @Column(name = "image_url")
    private String imageUrl;

    // 하나의 OCR 데이터는 여러 바운딩 박스를 가질 수 있음
    @OneToMany(mappedBy = "ocrData", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<OCRBoundingBox> boundingBoxList = new ArrayList<>();

    @Builder
    public OCRData(Long rawDataId, String imageUrl) {
        this.rawDataId = rawDataId;
        this.imageUrl = imageUrl;
    }

    public void addBoundingBox(OCRBoundingBox ocrBoundingBox) {
        ocrBoundingBox.setOcrData(this);
        this.boundingBoxList.add(ocrBoundingBox);
    }
}
