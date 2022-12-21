package ru.gb.internetshop.core.tests;

import api.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.internetshop.core.entities.Category;
import ru.gb.internetshop.core.repositories.ProductRepository;
import ru.gb.internetshop.core.services.CategoryService;
import ru.gb.internetshop.core.services.ProductService;

import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class ProductServiceAddProductTests {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private CategoryService categoryService;
    @Test
    public void createNewProductTest() {
        Category category = new Category();
        category.setId(10L);
        category.setTitle("Прочее");
        category.setProducts(Collections.emptyList());
        Mockito.doReturn(Optional.of(category))
                .when(categoryService)
                .findByTitle("Прочее");

        ProductDto productDto = new ProductDto(null, "Подгузники", 1000, "Прочее");
        productService.addProduct(productDto);

        Mockito.verify(productRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }



}
