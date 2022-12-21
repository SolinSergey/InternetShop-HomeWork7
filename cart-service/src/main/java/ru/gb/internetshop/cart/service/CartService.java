package ru.gb.internetshop.cart.service;

import api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.internetshop.cart.exceptions.ResourceNotFoundException;
import ru.gb.internetshop.cart.integrations.ProductServiceIntegration;
import ru.gb.internetshop.cart.utils.Cart;
import ru.gb.internetshop.cart.utils.CartItem;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart cart;
    private final ProductServiceIntegration productServiceIntegration;

    @PostConstruct
    public void init(){
        cart=new Cart();
        cart.setItems(new ArrayList<>());
    }

    public Cart getCurrentCart(){
        return cart;
    }

    public void subToCart(Long productId){
        System.out.println(cart);
        for (CartItem i: cart.getItems()){
            if (productId.equals(i.getProductId()) && i.getAmount()>1) {
                i.decrementAmount();
                break;
            }else if (productId.equals(i.getProductId())){
                cart.remove(i);
                break;
            }
        }
    }

    public void removeItem(Long productId){
        for (CartItem i: cart.getItems()){
            if (productId.equals(i.getProductId())){
                cart.remove(i);
                break;
            }
        }
    }
    public void addToCart(Long productId){
        ProductDto p = productServiceIntegration.findById(productId);
        cart.add(p);
    }

    public void clearCart(){
        cart.clear();
    }
}
