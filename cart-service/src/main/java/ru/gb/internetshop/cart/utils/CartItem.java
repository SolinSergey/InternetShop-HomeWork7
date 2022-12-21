package ru.gb.internetshop.cart.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItem {

    private Long productId;
    private String productTitle;
    private int amount;
    private int price;

    public void incrementAmount() {
        amount++;
    }

    public void decrementAmount() {
        amount--;
    }
}
