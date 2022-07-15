package com.devnus.belloga.labeling.data.service;

import com.devnus.belloga.labeling.data.domain.OCRData;
import com.devnus.belloga.labeling.data.dto.ResponseData;
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
    /**
     * 랜덤으로 라벨링 되지 않은 OCR 데이터 중 하나를 가져온다.
     */
    @Transactional(readOnly = true)
    @Override
    public Optional<ResponseData.OCRTargetData> getRandomTargetOCRData() {
        // https://dazbee.tistory.com/49?category=1040314
        long count = ocrDataRepository.countByIsLabeled(false); // 라벨이 되지 않은 OCR 데이터의 카운트를 구한다.
        int idx = (int)(Math.random() * count); // 가져온 개수 중 랜덤한 하나의 인덱스를 뽑는다.

        Page<OCRData> targetPage = ocrDataRepository.findAllByIsLabeled(PageRequest.of(idx, 1), false);

        // 라벨링할 데이터가 없으면 일단 null 반환
        if(!targetPage.hasContent()) {
            return Optional.ofNullable(null);
        }
        return Optional.ofNullable(ResponseData.OCRTargetData.of(targetPage.getContent().get(0)));
    }
}
