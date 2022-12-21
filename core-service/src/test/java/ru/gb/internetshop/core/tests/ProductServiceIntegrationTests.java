package ru.gb.internetshop.core.tests;

import api.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.internetshop.core.entities.Product;
import ru.gb.internetshop.core.repositories.ProductRepository;
import ru.gb.internetshop.core.services.ProductService;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ProductServiceIntegrationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void findAllTest(){
        List<ProductDto> productDtoList;
        productDtoList=productService.findAll();
        Assertions.assertEquals(22, productDtoList.size());
    }

    @Test
    public void findById(){
        Optional<Product> product;
        product=productService.findById(19L);
        Assertions.assertEquals("Зефир",product.get().getTitle());
    }
}
