package ru.gb.internetshop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.internetshop.core.entities.Category;
import ru.gb.internetshop.core.repositories.CategoryRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public Optional<Category> findByTitle (String title){
        return categoryRepository.findByTitle(title);
    }
}
