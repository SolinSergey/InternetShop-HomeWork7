package ru.gb.internetshop.core.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.internetshop.core.converters.ProductConverter;
import api.ProductDto;
import ru.gb.internetshop.core.entities.Product;
import ru.gb.internetshop.core.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductConverter productConverter;
    private CategoryService categoryService;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService=categoryService;
    }

    @Autowired
    public void setProductConverter(ProductConverter productConverter) {
        this.productConverter=productConverter;
    }
    public ProductService() {
    }

    public List<ProductDto> findAll() {
        List<Product> productList=productRepository.findAll();
        List<ProductDto> products=new ArrayList<>();
        for(Product p:productList){
            products.add(new ProductDto(p.getId(),p.getTitle(),p.getPrice(),p.getCategory().getTitle()));
        }
        return products;
    }

    public Optional<Product> findById(Long id) {
        Optional<Product> product=null;
        product=productRepository.findById(id);
        return product;
    }


    public void addProduct(ProductDto product) {
        Product product1=new Product();
        product1.setPrice(product.getPrice());
        product1.setTitle(product.getTitle());
        product1.setCategory(categoryService.findByTitle(product.getCategory()).get());
        productRepository.save(product1);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<Product> findProductByMoreMaxPrice(int maxPrice){
        return productRepository.findAllByMoreMaxPrice(maxPrice);
    }

    public List<Product> findProductByLessMaxPrice(int maxPrice){
        return productRepository.findAllByLessMaxPrice(maxPrice);
    }

    public List<Product> findProductByPriceBetween(int minPrice, int maxPrice){
        return productRepository.findProductByPriceBetween(minPrice,maxPrice);
    }

    public void saveProduct (Product product){
        productRepository.save(product);
    }
}
