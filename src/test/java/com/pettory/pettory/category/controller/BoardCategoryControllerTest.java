package com.pettory.pettory.category.controller;

import com.pettory.pettory.category.entity.BoardCategoryEntity;
import com.pettory.pettory.category.service.BoardCategoryService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BoardCategoryController.class)
public class BoardCategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BoardCategoryService boardCategoryService;

    @InjectMocks
    private BoardCategoryController boardCategoryController;

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testCreateCategoryWithAdminRole() throws Exception {
        String categoryTitle = "New Category";

        when(boardCategoryService.createCategory(anyString())).thenReturn(new BoardCategoryEntity(categoryTitle));

        mockMvc.perform(post("/categories/create")
                        .param("title", categoryTitle)
                        .with(csrf()))  // CSRF 토큰 추가
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.categoryTitle").value(categoryTitle));

        verify(boardCategoryService, times(1)).createCategory(anyString());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testCreateCategoryWithUserRole() throws Exception {
        mockMvc.perform(post("/categories/create")
                        .param("title", "New Category")
                        .with(csrf()))  // CSRF 토큰 추가
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testUpdateCategoryWithAdminRole() throws Exception {
        int categoryNum = 1;
        String newTitle = "Updated Category";

        BoardCategoryEntity categoryEntity = new BoardCategoryEntity();
        categoryEntity.setCategoryTitle(newTitle);

        when(boardCategoryService.updateCategory(anyInt(), anyString())).thenReturn(categoryEntity);

        mockMvc.perform(put("/categories/update/{categoryNum}", categoryNum)
                        .param("newTitle", newTitle)
                        .with(csrf()))  // CSRF 토큰 추가
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.categoryTitle").value(newTitle));

        verify(boardCategoryService, times(1)).updateCategory(anyInt(), anyString());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testUpdateCategoryWithUserRole() throws Exception {
        mockMvc.perform(put("/categories/update/1")
                        .param("newTitle", "Updated Category")
                        .with(csrf()))  // CSRF 토큰 추가
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void testDeleteCategoryWithAdminRole() throws Exception {
        int categoryNum = 1;

        doNothing().when(boardCategoryService).deleteCategory(categoryNum);

        mockMvc.perform(delete("/categories/delete/{categoryNum}", categoryNum)
                        .with(csrf()))  // CSRF 토큰 추가
                .andExpect(status().isNoContent());

        verify(boardCategoryService, times(1)).deleteCategory(anyInt());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void testDeleteCategoryWithUserRole() throws Exception {
        mockMvc.perform(delete("/categories/delete/1")
                        .with(csrf()))  // CSRF 토큰 추가
                .andExpect(status().isForbidden());
    }
}
