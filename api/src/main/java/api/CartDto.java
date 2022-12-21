package api;

import java.util.ArrayList;
import java.util.List;


public class CartDto {
    List<CartItemDto> items=new ArrayList<>();
    private int totalPrice;

    public CartDto() {
    }

    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int calcTotalPrice(){
        totalPrice=0;
        for (CartItemDto i:items){
            totalPrice+=i.getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        return "CartDto{" +
                "items=" + items +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
