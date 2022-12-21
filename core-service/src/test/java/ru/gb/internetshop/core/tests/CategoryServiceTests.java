package ru.gb.internetshop.core.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.gb.internetshop.core.entities.Category;
import ru.gb.internetshop.core.entities.Product;
import ru.gb.internetshop.core.repositories.CategoryRepository;
import ru.gb.internetshop.core.services.CategoryService;

import java.util.Optional;

@SpringBootTest
public class CategoryServiceTests {

    @Autowired
    private CategoryService categoryService;
    @Test
    public void findByTitleTest(){
        Optional<Category> category;
        category=categoryService.findByTitle("Рыба");
        Assertions.assertEquals(3L,category.get().getId());
    }
}
