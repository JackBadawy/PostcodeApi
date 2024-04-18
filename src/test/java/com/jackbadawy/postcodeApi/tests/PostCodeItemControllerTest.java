package com.jackbadawy.postcodeApi.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.BDDMockito.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jackbadawy.postcodeApi.posts.CreatePostcodeItemDTO;
import com.jackbadawy.postcodeApi.posts.PostCodeItem;
import com.jackbadawy.postcodeApi.posts.PostCodeItemController;
import com.jackbadawy.postcodeApi.posts.PostCodeItemService;

@WebMvcTest(PostCodeItemController.class)
@ExtendWith(SpringExtension.class)
public class PostCodeItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostCodeItemService postCodeItemService;

    @Test 
    public void testCreatePost_ValidData_ReturnsCreated() throws Exception {
    	CreatePostcodeItemDTO validPostcodeItem = new CreatePostcodeItemDTO("Suburb", "1234");
    	PostCodeItem createdItem = new PostCodeItem();
    	
    	given(postCodeItemService.createItem(any(CreatePostcodeItemDTO.class))).willReturn(createdItem);
    	
    	mockMvc.perform(post("/items")
    			.contentType(MediaType.APPLICATION_JSON)
    			.content(new ObjectMapper().writeValueAsString(validPostcodeItem)))
    			.andExpect(status().isCreated());
    }
}
