package com.devnus.belloga.labeling.data.controller;

import com.devnus.belloga.labeling.common.dto.CommonResponse;
import com.devnus.belloga.labeling.data.domain.DataType;
import com.devnus.belloga.labeling.data.service.OCRDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * DataController 는 라벨링 대상 데이터에 관한 컨트롤러이다.
 * @author suhongkim

 */
@RestController
@RequestMapping("/api")
public class DataController {
    private final OCRDataService ocrDataService;

    /**
     * 생성자 주입
     */
    public DataController(OCRDataService ocrDataService) {
        this.ocrDataService = ocrDataService;
    }

    /**
     * 라벨링을 수행할 대상 데이터를 반환한다. 현재는 OCR 데이터만 반환하지만 추 후 다른 형태의 데이터도 라벨링이 가능하도록 확장성 있게 구현한다.
     * 타켓 데이터란 라벨링 수행해야하는 대상 데이터를 의미함
     * @param type
     */
    @GetMapping("/v1/data/target/{type}")
    public ResponseEntity<CommonResponse> requestTargetData(@PathVariable("type")DataType type) {
        Object result = null; // OCR 타입 외 다른 유형의 데이터도 받도록 result를 별도로 선언

        switch (type) {
            case OCR:
                result = ocrDataService.getRandomTargetOCRData();
                break;
        }

        return new ResponseEntity<>(CommonResponse.builder()
                .success(true)
                .response(result)
                .build(), HttpStatus.OK);
    }
}
