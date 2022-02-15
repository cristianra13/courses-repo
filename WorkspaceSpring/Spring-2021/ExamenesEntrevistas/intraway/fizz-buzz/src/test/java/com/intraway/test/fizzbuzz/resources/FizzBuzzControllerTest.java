package com.intraway.test.fizzbuzz.resources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@EnableWebMvc
class FizzBuzzControllerTest {

    private final String URL = "/intraway/api/fizzbuzz/";
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void validNumbersOkTest() throws Exception {
        mockMvc.perform(
                get(URL + "/" + 1 + "/" +15)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void validNumbersBadRequestTest() throws Exception {
        mockMvc.perform(
          get(URL + "1/ACDC")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest());
    }

    @Test
    public void validNumbers404Test() throws Exception {
        mockMvc.perform(
                get(URL)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().is4xxClientError());
    }

}