package com.prueba.Prueba.controller;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class PriceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String API_ROOT_PATH = "/api";

    private static final Logger LOGGER = LoggerFactory.getLogger(PriceControllerTest.class);

    @Test
    public void getPriceByPriorityTestWithSuccess200() throws Exception {
        String response = mockMvc.perform(get(API_ROOT_PATH +"/getPriceByPriority")
                .param("applicationDate", "2020-12-14 15.00.00")
                .param("brandId", String.valueOf(1))
                .param("productId", String.valueOf(35455)))
            .andExpect(status().is(HttpStatus.OK.value()))
            .andReturn().getResponse().getContentAsString();

        LOGGER.info("response: " + response);
    }

    @Test
    public void getPriceByPriorityTestWithNoContent204() throws Exception {
        String response = mockMvc.perform(get(API_ROOT_PATH +"/getPriceByPriority")
                .param("applicationDate", "2020-12-14 15.00.00")
                .param("brandId", String.valueOf(2))
                .param("productId", String.valueOf(35455)))
            .andExpect(status().is(HttpStatus.NO_CONTENT.value()))
            .andReturn().getResponse().getContentAsString();

        LOGGER.info("response: " + response);
    }
}