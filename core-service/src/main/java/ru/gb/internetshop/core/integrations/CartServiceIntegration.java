package ru.gb.internetshop.core.integrations;

import api.CartDto;
import api.ProductDto;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/")
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public ResponseEntity clearCart() {
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/clear")
                .retrieve()
                .bodyToMono(ResponseEntity.class)
                .block();
    }

}
