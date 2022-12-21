package ru.gb.internetshop.cart;

import api.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTests {
    @Autowired
    private MockMvc mvc;

    @Test
    public void getCurrentCartTest() throws Exception {
        ProductDto productDto = new ProductDto(null, "Demo", 100, "Рыба");
        mvc
                .perform(
                        get("/api/v1/cart")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new ObjectMapper().writeValueAsString(productDto))
                                .header("username", "serg") // Здесь не особо нужно
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void addProductTest() throws Exception {
        ProductDto productDto = new ProductDto(null, "Demo", 100, "Рыба");
        mvc.perform(get("/api/v1/cart/add/4").header("username","serg")).andExpect(status().isOk());
    }
    @Test
    public void subProductTest() throws Exception {
        mvc.perform(get("/api/v1/cart/add/4").header("username", "serg"));
        mvc.perform(get("/api/v1/cart/add/4").header("username", "serg"));
        mvc.perform(get("/api/v1/cart/sub/4").header("username", "serg")).andExpect(status().isOk());
    }

    @Test
    public void clearCartTest() throws Exception {
        mvc.perform(get("/api/v1/cart/add/4").header("username", "serg"));
        mvc.perform(get("/api/v1/cart/add/4").header("username", "serg"));
        mvc.perform(get("/api/v1/cart/sub/4").header("username", "serg"));
        mvc.perform(get("/api/v1/cart/").header("username", "serg"));
        mvc.perform(get("/api/v1/cart/clear").header("username", "serg")).andExpect(status().isOk());
    }

}
