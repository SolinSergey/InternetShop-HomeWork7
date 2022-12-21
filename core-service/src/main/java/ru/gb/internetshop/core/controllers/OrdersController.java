package ru.gb.internetshop.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.internetshop.core.integrations.CartServiceIntegration;
import ru.gb.internetshop.core.services.OrderService;
//import ru.gb.internetshop.core.services.OrderService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrdersController {
   private final OrderService orderService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username){
        System.out.println(username);
        orderService.createOrder(username);
    }
}
