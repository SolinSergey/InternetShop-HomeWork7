package ru.gb.internetshop.cart.converters;

import api.CartDto;
import api.CartItemDto;
import org.springframework.stereotype.Component;
import ru.gb.internetshop.cart.utils.Cart;
import ru.gb.internetshop.cart.utils.CartItem;


@Component
public class CartConverter {
    public CartDto entityToCartDto(Cart cart) {
        CartDto cartDto = new CartDto();

        for (CartItem i : cart.getItems()) {
            CartItemDto cartItemDto = new CartItemDto();
            cartItemDto.setId(i.getProductId());
            cartItemDto.setTitle(i.getProductTitle());
            cartItemDto.setPrice(i.getPrice());
            cartItemDto.setAmount(i.getAmount());
            cartItemDto.calcTotalPrice();
            cartDto.getItems().add(cartItemDto);
        }
        cartDto.calcTotalPrice();
        return cartDto;
    }
}
