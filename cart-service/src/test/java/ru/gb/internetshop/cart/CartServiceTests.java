package ru.gb.internetshop.cart;

import api.ProductDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.internetshop.cart.integrations.ProductServiceIntegration;
import ru.gb.internetshop.cart.service.CartService;
import ru.gb.internetshop.cart.utils.CartItem;

@SpringBootTest
public class CartServiceTests {
    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Autowired
    private CartService cartService;

    @Test
    public void initTest(){
        cartService.init();
        Assertions.assertEquals(0, cartService.getCurrentCart().getItems().size());
    }

    @Test
    public void addToCart(){
        ProductDto productDto1=new ProductDto(1L, "Селедка", 500, "Рыба");
        ProductDto productDto2=new ProductDto(2L, "Лимонад", 500, "Бакалея");
        cartService.init();
        Mockito.doReturn(productDto1)
                .when(productServiceIntegration)
                .findById(1L);
        Mockito.doReturn(productDto2)
                .when(productServiceIntegration)
                .findById(2L);
        cartService.addToCart(1L);
        cartService.addToCart(2L);
        Assertions.assertEquals(2,cartService.getCurrentCart().getItems().size());
    }

    @Test
    public void addtoCart2(){
        ProductDto productDto1=new ProductDto(1L, "Селедка", 500, "Рыба");
        ProductDto productDto2=new ProductDto(2L, "Лимонад", 500, "Бакалея");
        cartService.init();
        Mockito.doReturn(productDto1)
                .when(productServiceIntegration)
                .findById(1L);
        Mockito.doReturn(productDto2)
                .when(productServiceIntegration)
                .findById(2L);
        cartService.addToCart(1L);
        Long testId=2L;
        int count1=0;
        for (int i=0;i<3;i++){
            cartService.addToCart(testId);
            ++count1;
        }
        int count2=0;
        for (CartItem i:cartService.getCurrentCart().getItems()){
            if (i.getProductId().equals(testId)){
                count2=i.getAmount();
            }
        }
        Assertions.assertEquals(count1,count2);
    }

    @Test
    public void subtoCart(){
        ProductDto productDto1=new ProductDto(1L, "Селедка", 500, "Рыба");
        ProductDto productDto2=new ProductDto(2L, "Лимонад", 500, "Бакалея");
        cartService.init();
        Mockito.doReturn(productDto1)
                .when(productServiceIntegration)
                .findById(1L);
        Mockito.doReturn(productDto2)
                .when(productServiceIntegration)
                .findById(2L);
        cartService.addToCart(1L);
        Long testId=2L;
        int count1=0;
        for (int i=0;i<3;i++){
            cartService.addToCart(testId);
            ++count1;
        }
        cartService.subToCart(testId);
        --count1;
        int count2=0;
        for (CartItem i:cartService.getCurrentCart().getItems()){
            if (i.getProductId().equals(testId)){
                count2=i.getAmount();
            }
        }
        Assertions.assertEquals(count1,count2);
    }

    @Test
    public void clearCart(){
        ProductDto productDto1=new ProductDto(1L, "Селедка", 500, "Рыба");
        ProductDto productDto2=new ProductDto(2L, "Лимонад", 500, "Бакалея");
        cartService.init();
        Mockito.doReturn(productDto1)
                .when(productServiceIntegration)
                .findById(1L);
        Mockito.doReturn(productDto2)
                .when(productServiceIntegration)
                .findById(2L);
        cartService.addToCart(1L);
        Long testId=2L;
        int count1=0;
        for (int i=0;i<3;i++){
            cartService.addToCart(testId);
            ++count1;
        }
        cartService.subToCart(testId);
        cartService.clearCart();
        Assertions.assertEquals(0,cartService.getCurrentCart().getItems().size());
    }
}
