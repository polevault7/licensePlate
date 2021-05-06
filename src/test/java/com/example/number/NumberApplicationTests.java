package com.example.number;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc
class NumberApplicationTests {
    @Autowired
    MockMvc mockMvc;

    void crudTest() throws Exception {
        mockMvc.perform(get("/numbers/1"))
                .andExpect(
                        content()
                                .json("{\n" +
                                        "    \"id\": 2,\n" +
                                        "    \"aircraftId\": 2,\n" +
                                        "    \"sourceCity\": \"KAZAN\",\n" +
                                        "    \"destCity\": \"MOSCOW\",\n" +
                                        "    \"departureTime\": 1608163200000,\n" +
                                        "    \"arrivalTime\": 1500000,\n" +
                                        "    \"journeyDuration\": 10000,\n" +
                                        "    \"price\": 5000\n" +
                                        "  }")
                );
    }
}
