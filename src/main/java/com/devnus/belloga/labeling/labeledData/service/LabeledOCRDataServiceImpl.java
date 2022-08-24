package com.devnus.belloga.labeling.labeledData.service;

import com.devnus.belloga.labeling.common.exception.error.NotFoundDataException;
import com.devnus.belloga.labeling.data.domain.OCRBoundingBox;
import com.devnus.belloga.labeling.data.repository.OCRBoundingBoxRepository;
import com.devnus.belloga.labeling.labeledData.domain.LabeledOCRData;
import com.devnus.belloga.labeling.labeledData.domain.LabelingVerificationStatus;
import com.devnus.belloga.labeling.labeledData.dto.ResponseLabeledOCRData;
import com.devnus.belloga.labeling.labeledData.event.LabeledDataProducer;
import com.devnus.belloga.labeling.labeledData.repository.LabeledOCRDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LabeledOCRDataServiceImpl implements LabeledOCRDataService {
    private final LabeledOCRDataRepository labeledOCRDataRepository;
    private final OCRBoundingBoxRepository ocrBoundingBoxRepository;
    private final LabeledDataProducer labeledDataProducer;

    /**
     * OCR데이터 바운딩박스에 대해 라벨링을 수행한다.
     * @param labelerId
     * @param boundingBoxId
     * @param textLabel
     */
    @Transactional
    @Override
    public void labelingBoundingBox(String labelerId, Long boundingBoxId, String textLabel) {
        // 라벨링을 적재한다.
        OCRBoundingBox ocrBoundingBox = ocrBoundingBoxRepository.findById(boundingBoxId)
                .orElseThrow(()->new NotFoundDataException());

        LabeledOCRData labeledOCRData = LabeledOCRData.builder()
                .labelerId(labelerId)
                .ocrBoundingBox(ocrBoundingBox)
                .textLabel(textLabel)
                .build();

        labeledOCRData = labeledOCRDataRepository.save(labeledOCRData);

        // 카프카로 임시 포인트를 지급 이벤트를 발행한다.
        labeledDataProducer.payTmpPointToLabeler(labelerId, labeledOCRData.getLabelingUUID(), 15L); // 현재는 15포인트 고정 지급, 논의 필요
        // 라벨링 검증을 위해 검증 마이크로서비스에 이벤트를 전달한다.
        labeledDataProducer.producingOCRBoundingBoxLabelingEvent(ocrBoundingBox.getId(), labeledOCRData.getTextLabel(), labeledOCRData.getLabelingUUID());
    }

    /**
     * labelingUUID 에 대해 status 를 SUCCESS 로 변경한다.
     * WAIT SUCCESS FAIL
     * @param labelingUUID
     */
    @Transactional
    @Override
    public void changeLabelingVerificationStatusSuccess(String labelingUUID) {
        // uuid 에 대해 상태 변경
        LabeledOCRData data = labeledOCRDataRepository.findByLabelingUUID(labelingUUID)
                .orElseThrow(()->new NotFoundDataException());
        data.changeLabelingVerificationStatus(LabelingVerificationStatus.SUCCESS);
        // 통과 시 알맞은 이벤트를 발행하는 비동기 통신 진행해야 함
            // 신뢰도를 올림
            // 임시포인트를 포인트로 변환
        //
    }

    /**
     * labelingUUID 에 대해 status 를 FAIL 로 변경한다.
     * WAIT SUCCESS FAIL
     * @param labelingUUID
     */
    @Transactional
    @Override
    public void changeLabelingVerificationStatusFail(String labelingUUID) {
        // uuid 에 대해 상태 변경
        LabeledOCRData data = labeledOCRDataRepository.findByLabelingUUID(labelingUUID)
                .orElseThrow(()->new NotFoundDataException());
        data.changeLabelingVerificationStatus(LabelingVerificationStatus.FAIL);
        // 불통 시 알맞은 이벤트를 발행하는 비동기 통신 진행해야 함
            // 신뢰도를 내림
            // 포인트를 회수
        //
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResponseLabeledOCRData.LabeledOCRDataInfo> getMyLabelingInfo(Pageable pageable, String labelerId) {
        Page<LabeledOCRData> pages = labeledOCRDataRepository.findByLabelerId(pageable, labelerId);
        return pages.map(ResponseLabeledOCRData.LabeledOCRDataInfo::of);
    }
}
