package com.devnus.belloga.labeling.labeledData.controller;

import com.devnus.belloga.labeling.common.aop.annotation.UserRole;
import com.devnus.belloga.labeling.common.aop.annotation.GetAccountIdentification;
import com.devnus.belloga.labeling.common.dto.CommonResponse;
import com.devnus.belloga.labeling.data.domain.DataType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LabeledDataController 는 라벨링 수행 후 적재에 관한 컨트롤러이다.
 * @author suhongkim
 */
@RestController
@RequestMapping("/api")
public class LabeledDataController {

    /**
     * 라벨링을 수행한다.
     * @param labelerId
     * @return CommonResponse
     */
    @PostMapping("/v1/data/labeling/{type}")
    public ResponseEntity<CommonResponse> labelingData(@PathVariable("type") DataType type,
                                                              @GetAccountIdentification(role = UserRole.LABELER) String labelerId) {
        System.out.println(labelerId);
        System.out.println(type);

        return new ResponseEntity<>(CommonResponse.builder()
                .success(true)
                .build(), HttpStatus.OK);
    }
}
