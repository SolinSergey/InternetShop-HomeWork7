package ru.gb.internetshop.cart.converters;

import api.CartItemDto;
import org.springframework.stereotype.Component;
import ru.gb.internetshop.cart.utils.CartItem;


@Component
public class CartItemConverter {
    public CartItemDto entityToDto(CartItem c) {
        CartItemDto dto=new CartItemDto(c.getProductId(), c.getProductTitle(), c.getAmount(), c.getPrice(),0);
        dto.calcTotalPrice();
        return dto;
    }
}