package com.hhs.product;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
class ProductApplicationTests {

    @Autowired
    private MockMvc mockMvc;
//1. 상품 등록(Create)
//2. 상품 상세 조회(Read)
//3. 상품 리스트 전체 조회(Read)
//4. 상품 리스트 페이징 조회(Read + Pagination))
//5. 상품 수정 (Update)
//6. 상품 삭제 (Delete)
    /**
     * 상품등록 테스트
     */
    @Test
    void create() throws Exception {
        //전체 제품 검색
        MvcResult result = this.mockMvc
                //테스트 url
                .perform(get("/api/create")
                        .param("name", "제품명2")
                        .param("price", "100000")
                )
                //연결상태 확인
                .andExpect(status().isOk())
                .andReturn();

        this.mockMvc
                //테스트 url
                .perform(get("/api/select")
                        .param("name", "제품명2")
                )
                //연결상태 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andDo(print());
    }

    @Test
    void selectTarget() throws Exception {
        this.mockMvc
                //테스트 url
                .perform(get("/api/select")
                        .param("name", "제품명1")
                )
                //연결상태 확인
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(greaterThan(0))))
                .andDo(print());
    }

    @Test
    void select() throws Exception {
        //전체 제품 검색
        this.mockMvc
                //테스트 url
                .perform(get("/api/select"))
                //연결상태 확인
                .andExpect(status().isOk())
                //한개보다 큰지(전체리스트인지)
                .andExpect(jsonPath("$.*", hasSize(greaterThan(1))));

        //단일 제품 검색
        this.mockMvc
                .perform(
                        get("/api/select")
                                .param("productId", "1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)));
    }

    @Test
    void update() throws Exception {
        String changeName = "변경된이름";
        int changePrice = 9999;

        //전체 제품 검색
        this.mockMvc
                //테스트 url
                .perform(get("/api/update")
                        .param("productId", "1")
                        .param("name", changeName)
                        .param("price", String.valueOf(changePrice))
                )
                //연결상태 확인
                .andExpect(status().isOk())
                .andExpect(result -> "success".equals(result.getResponse().getContentAsString()));

        //변경된 제품명 확인.
        this.mockMvc
                .perform(
                        get("/api/select")
                                .param("productId", "1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(1)))
                .andExpect(jsonPath("$.[0].name", is(changeName)))
                .andExpect(jsonPath("$.[0].price", is(changePrice)))
                .andDo(print());
    }

}
