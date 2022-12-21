package ru.gb.internetshop.core.converters;

import org.springframework.stereotype.Component;
import ru.gb.internetshop.core.entities.Product;
import api.ProductDto;

@Component
public class ProductConverter {
    public ProductDto entityToProductDto (Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setTitle(product.getTitle());
        productDto.setPrice(product.getPrice());
        productDto.setCategory(product.getCategory().getTitle());
        return productDto;
    }
}
