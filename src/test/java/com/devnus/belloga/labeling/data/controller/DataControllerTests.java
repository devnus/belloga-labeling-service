package com.devnus.belloga.labeling.data.controller;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@ActiveProfiles("test")
public class DataControllerTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("OCR 라벨링 대상 데이터 요청 API 성공 테스트")
    void OCR_라벨링_대상_데이터_요청_API_성공 () throws Exception {
        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/v1/data/target/{dataType}", DataType.OCR)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("request-ocr-target-data",
                        pathParameters(
                                parameterWithName("dataType").description("라벨링 데이터 타입")
                        ),
                        responseFields(
                                fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("정상 응답 여부"),
                                fieldWithPath("response.dataType").description("응답 데이터의 라벨링 데이터 타입"),
                                fieldWithPath("response.ocrDataId").description("OCR 데이터 id"),
                                fieldWithPath("response.imageUrl").description("OCR 데이터 이미지"),
                                fieldWithPath("response.isLabeled").description("레이블링 여부"),
                                fieldWithPath("response.boundingBox.[]").description("바운딩 박스 정보"),
                                fieldWithPath("response.boundingBox.[].x").description("바운딩 박스의 x좌표 리스트").optional().type(JsonFieldType.ARRAY),
                                fieldWithPath("response.boundingBox.[].y").description("바운딩 박스의 y좌표 리스트").optional().type(JsonFieldType.ARRAY),
                                fieldWithPath("error").description("error 발생 시 에러 정보")
                        )
                ));
    }
}
