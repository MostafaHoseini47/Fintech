package com.snapp.fintech.controller;

import com.snapp.fintech.FintechApplicationTests;
import com.snapp.fintech.config.security.service.dto.request.SignUpRequest;
import com.snapp.fintech.service.CategoryService;
import com.snapp.fintech.service.dto.CategoryDto;
import com.snapp.fintech.util.BaseTestUtils;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = { FintechApplicationTests.class})
@RunWith(SpringRunner.class)
public class CategoryControllerTest extends BaseTestUtils {

    @MockBean
    private CategoryService categoryService;

    private String token;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() throws Exception {
        SignUpRequest request = new SignUpRequest();
        request.setUserName("testUser");
        request.setPassword("password123");
        request.setFirstName("mostaf");
        request.setLastName("hoseini");
        request.setStock(BigDecimal.valueOf(20000.0));
        token = performSignInAndGetToken(request);
        createCategoriesDto();
    }

    private List<CategoryDto> createCategoriesDto() {
        List<CategoryDto> categories = new ArrayList<>();

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("clothes");
        categories.add(categoryDto);

        CategoryDto categoryDto1 = new CategoryDto();
        categoryDto.setId(2L);
        categoryDto.setName("supermarket");
        categories.add(categoryDto1);

        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto.setId(3L);
        categoryDto.setName("shoes");
        categories.add(categoryDto2);

        CategoryDto categoryDto3 = new CategoryDto();
        categoryDto.setId(4L);
        categoryDto.setName("health");
        categories.add(categoryDto3);

        return categories;
    }

    @Test
    public void testCreateCategory() throws Exception {
        for (CategoryDto category:createCategoriesDto()) {
            mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/categories")
                            .header("Authorization", "Bearer " + token)
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(category)))
                    .andExpect(MockMvcResultMatchers.status().isCreated());
        }

    }

    @Test
    public void updateCategory() throws Exception {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(3L);
        categoryDto.setName("hospital");

        mockMvc.perform(MockMvcRequestBuilders.put("/api/v1/categories")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(categoryDto.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(categoryDto.getName()));
    }

    @Test
    public void getByIdCategory() throws Exception {
        Long categoryId = 2L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        categoryDto.setName("supermarket");

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories/{id}", categoryId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(categoryId))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Test Category"));
    }

    @Test
    public void getAllCategory() throws Exception {

        List<CategoryDto> categories = createCategoriesDto();

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/categories")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()) // Expect HTTP status 200
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", Matchers.hasSize(4)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("clothes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].name").value("supermarket"));
    }

    @Test
    public void deleteCategory() throws Exception {
        Long categoryId = 4L;
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(categoryId);
        categoryDto.setName("health");

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/categories", categoryId)
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(categoryDto)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
