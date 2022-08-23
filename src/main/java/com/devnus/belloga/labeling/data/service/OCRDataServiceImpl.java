package com.devnus.belloga.labeling.data.service;

import com.devnus.belloga.labeling.common.exception.error.NotFoundDataException;
import com.devnus.belloga.labeling.data.domain.OCRAnnotationType;
import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import com.devnus.belloga.labeling.data.domain.OCRData;
import com.devnus.belloga.labeling.data.domain.RectanglePoint;
import com.devnus.belloga.labeling.data.dto.EventPreprocessing;
import com.devnus.belloga.labeling.data.dto.ResponseOCRData;
import com.devnus.belloga.labeling.data.event.DataProducer;
import com.devnus.belloga.labeling.data.repository.OCRBoundingBoxRepository;
import com.devnus.belloga.labeling.data.repository.OCRDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OCRDataServiceImpl implements OCRDataService {
    private final OCRDataRepository ocrDataRepository;
    private final OCRBoundingBoxRepository ocrBoundingBoxRepository;
    private DataProducer dataProducer;
    /**
     * 랜덤으로 라벨링 되지 않은 OCR바운딩박스에 해당하는 데이터 중 하나를 선택해 해당 OCR 데이터를 가져온다.
     * 즉 하나의 OCR데이터가 여러 OCR 바운딩박스를 가져도 라벨링되지 않은 OCR 바운딩박스 하나만 반환해주어야함
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<ResponseOCRData.OCRBoundingBox> getRandomTargetOCRData() {
        // https://dazbee.tistory.com/49?category=1040314
        long count = ocrBoundingBoxRepository.countByIsLabeled(false); // 라벨이 되지 않은 OCR 데이터의 카운트를 구한다.
        int idx = (int)(Math.random() * count); // 가져온 개수 중 랜덤한 하나의 인덱스를 뽑는다.

        Page<OCRBoundingBox> targetPage = ocrBoundingBoxRepository.findAllByIsLabeled(PageRequest.of(idx, 1), false);

        // 라벨링할 데이터가 없으면 일단 null 반환
        if(!targetPage.hasContent()) {
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(ResponseOCRData.OCRBoundingBox.of(targetPage.getContent().get(0)));
    }

    /**
     * OCR 라벨링 검증 성공 시 해당 바운딩박스는 라벨링 성공으로 변경
     * labelingResult로 결과를 비동기로 보낸다.
     * @param boundingBoxId
     * @param totalLabelerNum
     * @param reliability
     * @param textLabel
     */
    @Transactional
    @Override
    public void recordSuccessOCRLabelingResult(Long boundingBoxId, Long totalLabelerNum, Double reliability, String textLabel) {
        OCRBoundingBox ocrBoundingBox = ocrBoundingBoxRepository.findById(boundingBoxId)
                .orElseThrow(() -> new NotFoundDataException());
        ocrBoundingBox.changeLabeledStatus(true); // 검증된 라벨링 했습니당

        dataProducer.recordOCRVerificationResult(ocrBoundingBox.getOcrData().getEnterpriseId(), boundingBoxId, totalLabelerNum, reliability, textLabel); // 이벤트 전송
    }

    /**
     * 전처리된 OCR 데이터를 디비에 업로드한다.
     * @param enterpriseId
     * @param rawDataId
     * @param imageUrl
     * @param boundingBoxList
     */
    @Transactional
    @Override
    public void uploadPreprocessingData(String enterpriseId, Long rawDataId, String imageUrl, EventPreprocessing.BoundingBox[] boundingBoxList) {
        OCRData ocrData = OCRData.builder()
                .enterpriseId(enterpriseId)
                .rawDataId(rawDataId)
                .imageUrl(imageUrl)
                .build();

        for(int i = 0 ; i < boundingBoxList.length ; i++) {
            EventPreprocessing.BoundingBox info = boundingBoxList[i];
            ocrData.addBoundingBox(OCRBoundingBox.builder()
                            .annotationType(OCRAnnotationType.RECTANGLE)
                            .rectanglePoint(RectanglePoint.builder()
                                    .leftTopX(info.getX()[0])
                                    .leftDownX(info.getX()[1])
                                    .rightTopX(info.getX()[2])
                                    .rightDownX(info.getX()[3])
                                    .leftTopY(info.getY()[0])
                                    .leftDownY(info.getY()[1])
                                    .rightTopY(info.getY()[2])
                                    .rightDownY(info.getY()[3])
                                    .build())
                    .build());
        }

        ocrData = ocrDataRepository.save(ocrData);
    }
}
