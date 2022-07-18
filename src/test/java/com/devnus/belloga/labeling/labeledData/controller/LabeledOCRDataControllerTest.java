package com.devnus.belloga.labeling.labeledData.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
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
                mockMvc.perform(RestDocumentationRequestBuilders.post("/api/v1/ocr-data/labeling")
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

}

