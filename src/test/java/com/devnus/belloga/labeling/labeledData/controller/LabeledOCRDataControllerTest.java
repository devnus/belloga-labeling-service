package com.devnus.belloga.labeling.labeledData.controller;

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
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static com.epages.restdocs.apispec.MockMvcRestDocumentationWrapper.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
@EmbeddedKafka(
        brokerProperties = {
                "listeners=PLAINTEXT://localhost:9092"
        },
        ports = { 9092 })
public class LabeledOCRDataControllerTest {
        @Autowired
        private MockMvc mockMvc;
        @Autowired
        private ObjectMapper objectMapper;

        @Test
        void labelingOCRDataSuccessTest() throws Exception {
                //given
                Map<String, String> input = new HashMap<>();
                input.put("boundingBoxId", "1");
                input.put("label", "길동이");

                String labelerId = "hello-labeler";

                //when
                mockMvc.perform(RestDocumentationRequestBuilders.post("/api/labeled-data/v1/ocr-data")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input))
                                .header("labeler-id", labelerId) // 라벨링 수행하는 유저의 식별아이디, api gateway에서 받아온다.
                        )
                        //then
                        .andExpect(status().isOk())
                        .andDo(print())

                        //docs
                        .andDo(document("labeling-bounding-box-ocr-data",
                                requestFields(
                                        fieldWithPath("boundingBoxId").description("라벨링 하고자 하는 OCR 이미지의 바운딩박스 식별값"),
                                        fieldWithPath("label").description("레이블링 값")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                        fieldWithPath("dateTime").description("response time"),
                                        fieldWithPath("success").description("정상 응답 여부"),
                                        fieldWithPath("response").description("null"),
                                        fieldWithPath("error").description("error 발생 시 에러 정보")
                                )
                        ))
                        .andExpect(jsonPath("$.success", is(notNullValue())));
        }

        @Test
        @DisplayName("내가 라벨링한 정보 조회")
        void getMyLabelingInfo () throws Exception {
                mockMvc.perform(RestDocumentationRequestBuilders.get("/api/labeled-data/v1/ocr-data")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("labeler-id", "gildong")
                        )
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andDo(document("get-my-labeling-info-ocr",
                                responseFields(
                                        fieldWithPath("id").description("logging을 위한 api response 고유 ID"),
                                        fieldWithPath("dateTime").description("response time"),
                                        fieldWithPath("success").description("정상 응답 여부"),
                                        fieldWithPath("response.content.[]").description("content 정보"),
                                        fieldWithPath("response.content.[].labelingUUID").description("라벨링 UUID 정보").optional().type(JsonFieldType.STRING),
                                        fieldWithPath("response.content.[].labelingVerificationStatus").description("라벨링 검증 상태").optional().type(JsonFieldType.STRING),
                                        fieldWithPath("response.content.[].textLabel").description("내가 입력한 값").optional().type(JsonFieldType.STRING),

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

