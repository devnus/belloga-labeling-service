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
                input.put("label", "?????????");

                String labelerId = "hello-labeler";

                //when
                mockMvc.perform(RestDocumentationRequestBuilders.post("/api/labeled-data/v1/ocr-data")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(input))
                                .header("labeler-id", labelerId) // ????????? ???????????? ????????? ???????????????, api gateway?????? ????????????.
                        )
                        //then
                        .andExpect(status().isOk())
                        .andDo(print())

                        //docs
                        .andDo(document("labeling-bounding-box-ocr-data",
                                requestFields(
                                        fieldWithPath("boundingBoxId").description("????????? ????????? ?????? OCR ???????????? ??????????????? ?????????"),
                                        fieldWithPath("label").description("???????????? ???")
                                ),
                                responseFields(
                                        fieldWithPath("id").description("logging??? ?????? api response ?????? ID"),
                                        fieldWithPath("dateTime").description("response time"),
                                        fieldWithPath("success").description("?????? ?????? ??????"),
                                        fieldWithPath("response").description("null"),
                                        fieldWithPath("error").description("error ?????? ??? ?????? ??????")
                                )
                        ))
                        .andExpect(jsonPath("$.success", is(notNullValue())));
        }

        @Test
        @DisplayName("?????? ???????????? ?????? ??????")
        void getMyLabelingInfo () throws Exception {
                mockMvc.perform(RestDocumentationRequestBuilders.get("/api/labeled-data/v1/ocr-data")
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("labeler-id", "gildong")
                        )
                        .andExpect(status().isOk())
                        .andDo(print())
                        .andDo(document("get-my-labeling-info-ocr",
                                responseFields(
                                        fieldWithPath("id").description("logging??? ?????? api response ?????? ID"),
                                        fieldWithPath("dateTime").description("response time"),
                                        fieldWithPath("success").description("?????? ?????? ??????"),
                                        fieldWithPath("response.content.[]").description("content ??????"),
                                        fieldWithPath("response.content.[].labelingUUID").description("????????? UUID ??????").optional().type(JsonFieldType.STRING),
                                        fieldWithPath("response.content.[].labelingVerificationStatus").description("????????? ?????? ??????").optional().type(JsonFieldType.STRING),
                                        fieldWithPath("response.content.[].textLabel").description("?????? ????????? ???").optional().type(JsonFieldType.STRING),
                                        fieldWithPath("response.content.[].createdDate").description("???????????? ??????").optional().type(JsonFieldType.STRING),

                                        fieldWithPath("response.pageable.sort.unsorted").description("????????? ?????? sort ??????"),
                                        fieldWithPath("response.pageable.sort.sorted").description("????????? ?????? sort ??????"),
                                        fieldWithPath("response.pageable.sort.empty").description("????????? ?????? sort ??????"),
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

                                        fieldWithPath("error").description("error ?????? ??? ?????? ??????")
                                )
                        ));
        }
}

