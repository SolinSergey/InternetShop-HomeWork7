package ru.gb.internetshop.core.controllers;

import api.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.internetshop.core.converters.ProductConverter;
import ru.gb.internetshop.core.entities.Product;
import ru.gb.internetshop.core.exceptions.ResourceNotFoundException;
import ru.gb.internetshop.core.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    ProductService productService;
    ProductConverter productConverter;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @GetMapping
    public List<ProductDto> findAll() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productConverter.entityToProductDto(productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Продукт с id:" + id + " не найден")));
    }


    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody ProductDto product) {
        productService.addProduct(product);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
