package com.example.brickstore.controller;

import com.example.brickstore.entitiy.OrderRequest;
import com.example.brickstore.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    OrderService orderService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void create() throws Exception {

        OrderRequest orderRequest = new OrderRequest(2);

        mockMvc
                .perform(post("/order/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(orderRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
    }


    @Test
    void list() throws Exception {

        mockMvc
                .perform(get("/order/list")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("order_ref", "ORDER00001")
                )
                .andExpect(status().isOk());
    }
}