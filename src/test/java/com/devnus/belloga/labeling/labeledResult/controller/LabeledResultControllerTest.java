package com.devnus.belloga.labeling.labeledResult.controller;

import com.devnus.belloga.labeling.data.domain.DataType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
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
@EmbeddedKafka(
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092"
        },
        ports = { 9092 })
public class LabeledResultControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("검증된 OCR 데이터 및 신뢰도 조회 API 성공 테스트")
    void getVerificationOCRDataSuccess () throws Exception {
        // given
        Long targetProjectId = 1L;
        DataType targetType = DataType.OCR;
        String enterpriseId = "gildong";

        mockMvc.perform(RestDocumentationRequestBuilders.get("/api/labeled-result/v1/verification/results/{type}/{projectId}", targetType, targetProjectId)
                .contentType(MediaType.APPLICATION_JSON)
                .header("enterprise-id", enterpriseId)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andDo(document("request-verification-ocr-labeled-data",
                        pathParameters(
                                parameterWithName("type").description("라벨링 데이터 타입"),
                                parameterWithName("projectId").description("조회하고자하는 기업 사용자가 생성한 프로젝트 ID")
                        ),
                        responseFields(
                                fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                fieldWithPath("dateTime").description("response time"),
                                fieldWithPath("success").description("정상 응답 여부"),
                                fieldWithPath("response.content.[].imageUrl").description("이미지 경로"),
                                fieldWithPath("response.content.[].x").description("바운딩 박스 X 좌표"),
                                fieldWithPath("response.content.[].y").description("바운딩 박스 Y 좌표"),
                                fieldWithPath("response.content.[].textLabel").description("검증된 라벨링 값"),
                                fieldWithPath("response.content.[].totalLabelerNum").description("해당 데이터에 대해 라벨링을 수행한 사람의 수"),
                                fieldWithPath("response.content.[].reliability").description("검증 수행 신뢰도"),

                                fieldWithPath("response.pageable.sort.unsorted").description("페이징 처리 sort 정보"),
                                fieldWithPath("response.pageable.sort.sorted").description("페이징 처리 sort 정보"),
                                fieldWithPath("response.pageable.sort.empty").description("페이징 처리 sort 정보"),
                                fieldWithPath("response.pageable.pageNumber").description("page number"),
                                fieldWithPath("response.pageable.pageSize").description("page size"),
                                fieldWithPath("response.pageable.offset").description("page offset"),
                                fieldWithPath("response.pageable.paged").description("paged"),
                                fieldWithPath("response.pageable.unpaged").description("unpaged"),
                                fieldWithPath("response.totalPages").description("total pages"),
                                fieldWithPath("response.totalElements").description("total elements"),
                                fieldWithPath("response.last").description("last"),
                                fieldWithPath("response.numberOfElements").description("numberOfElements"),
                                fieldWithPath("response.size").description("size"),
                                fieldWithPath("response.sort.unsorted").description("unsorted"),
                                fieldWithPath("response.sort.sorted").description("sorted"),
                                fieldWithPath("response.sort.empty").description("empty"),
                                fieldWithPath("response.number").description("number"),
                                fieldWithPath("response.first").description("first"),
                                fieldWithPath("response.empty").description("empty"),

                                fieldWithPath("error").description("error 발생 시 에러 정보")
                        )
                ));
    }
}
