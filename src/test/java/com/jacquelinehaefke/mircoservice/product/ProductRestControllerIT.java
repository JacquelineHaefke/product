package com.jacquelinehaefke.mircoservice.product;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductRestController.class)
class ProductRestControllerIT {
    private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";
    private static final DateFormat dateFormat = new SimpleDateFormat(dateTimeFormat);
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;

    @BeforeAll
    static void beforeAll() {
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    @Test
    void shouldReturnProducts() throws Exception {
        final Product product = new Product("product1", 2.0);
        when(productRepository.findAll()).thenReturn(List.of(product));

        final String expected = "[{\"id\":" + product.getId() +
                ",\"name\":\"product1\",\"price\":2.0,\"created\":\"" +
                dateFormat.format(product.getCreated()) +
                "\",\"deleted\":false}]";

        this.mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @Test
    void shouldReturnNoProducts() throws Exception {
        this.mockMvc.perform(get("/api/v1/product"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json("[]"));
    }

    @Test
    void shouldCreateProducts() throws Exception {

        this.mockMvc.perform(post("/api/v1/product")
                .content("{\"name\":\"product1\", \"price\": 2.0}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldDeleteProducts() throws Exception {
        this.mockMvc.perform(delete("/api/v1/product/{id}", 1234)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
