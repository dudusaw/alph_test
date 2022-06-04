package com.example.alph_test.controller;

import com.example.alph_test.service.currency.CurrencyService;
import com.example.alph_test.service.gif.GifService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@MockBean(CurrencyService.class)
@MockBean(GifService.class)
class MainControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void compareCurrencyTest_noCodeQuery_shouldReturn400() throws Exception {
        mockMvc.perform(get("/currency/compare"))
                .andExpect(status().is4xxClientError()).andDo(print());
    }

    @Test
    void compareCurrencyTest() throws Exception {
        mockMvc.perform(get("/currency/compare").queryParam("code", "RUB"))
                .andExpect(status().is3xxRedirection()).andDo(print());
    }
}