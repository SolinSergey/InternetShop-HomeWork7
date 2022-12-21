package ru.gb.internetshop.core.services;
import api.CartDto;
import api.CartItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.internetshop.core.entities.Order;
import ru.gb.internetshop.core.entities.OrderItem;
import ru.gb.internetshop.core.entities.Product;
import ru.gb.internetshop.core.exceptions.ResourceNotFoundException;
import ru.gb.internetshop.core.integrations.CartServiceIntegration;
import ru.gb.internetshop.core.repositories.OrderItemRepository;
import ru.gb.internetshop.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    private final OrderItemRepository orderItemRepository;

    @Transactional
    public void createOrder(String username){
        CartDto cartDto = cartServiceIntegration.getCurrentCart();
        Order order=new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        List<OrderItem> orderItemList = new ArrayList<>();
        for (CartItemDto c: cartDto.getItems()){
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(c.getPrice());
            orderItem.setAmount(c.getAmount());
            Product product=productService.findById(c.getId()).orElseThrow(()->new ResourceNotFoundException("Продукт с id="+c.getId()+" не найден"));
            orderItem.setProduct(product);
            orderItemList.add(orderItem);
        }
        order.setItems(orderItemList);
        orderRepository.save(order);
        orderItemRepository.saveAll(orderItemList);
        cartServiceIntegration.clearCart();
    }
}
