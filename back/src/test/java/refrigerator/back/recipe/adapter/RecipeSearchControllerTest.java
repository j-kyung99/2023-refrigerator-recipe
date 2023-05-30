package refrigerator.back.recipe.adapter;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import refrigerator.back.authentication.application.port.out.CreateTokenPort;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class RecipeSearchControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired CreateTokenPort createTokenPort;


    @Test
    @DisplayName("레시피 조회")
    void findById() throws Exception {
        String memberId = "nhtest@gmail.com";
        String token = createTokenPort.createTokenWithDuration(memberId, "ROLE_STEADY_STATUS", 3000);
        mockMvc.perform(get("/api/recipe/1")
                .header(HttpHeaders.AUTHORIZATION, makeTokenHeader(token))
        ).andExpect(status().is2xxSuccessful()
        ).andDo(print());
    }

    @Test
    @DisplayName("레시피 검색")
    void search() throws Exception {
        String memberId = "nhtest@gmail.com";
        String token = createTokenPort.createTokenWithDuration(memberId, "ROLE_STEADY_STATUS", 3000);
        mockMvc.perform(get("/api/recipe/search?searchWord=두부&page=0")
                .header(HttpHeaders.AUTHORIZATION, makeTokenHeader(token))
        ).andExpect(status().is2xxSuccessful()
        ).andDo(print());
    }

    @Test
    @DisplayName("레시피 음식 유형 조회")
    void getConditionByFoodType() throws Exception {
        mockMvc.perform(get("/api/recipe/search/condition/food-type")
        ).andExpect(status().is2xxSuccessful()
        ).andDo(print());
    }

    @Test
    @DisplayName("레시피 카테고리 조회")
    void getConditionByCategory() throws Exception {
        mockMvc.perform(get("/api/recipe/search/condition/category")
        ).andExpect(status().is2xxSuccessful()
        ).andDo(print());
    }

    @Test
    @DisplayName("레시피 유형 조회")
    void getConditionByRecipeType() throws Exception {
        mockMvc.perform(get("/api/recipe/search/condition/recipe-type")
        ).andExpect(status().is2xxSuccessful()
        ).andDo(print());
    }

    @Test
    @DisplayName("레시피 난이도 조회")
    void getConditionByRecipeDifficulty() throws Exception {
        mockMvc.perform(get("/api/recipe/search/condition/difficulty")
        ).andExpect(status().is2xxSuccessful()
        ).andDo(print());
    }

    private String makeTokenHeader(String token){
        return "Bearer " + token;
    }
}