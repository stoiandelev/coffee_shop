package com.example.coffee_shop_exam.service.impl;

import com.example.coffee_shop_exam.model.entity.CategoryEntity;
import com.example.coffee_shop_exam.model.entity.CategoryNameEnum;
import com.example.coffee_shop_exam.repository.CategoryRepository;
import com.example.coffee_shop_exam.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initCategory() {

        if (categoryRepository.count() != 0) {
            return;
        }

        Arrays.stream(CategoryNameEnum.values())
                .forEach(categoryNameEnum -> {
                    CategoryEntity category = new CategoryEntity();
                    category.setName(categoryNameEnum);

                    switch (categoryNameEnum) {
                        case CAKE -> category.setNeededTime(10);
                        case COFFEE -> category.setNeededTime(2);
                        case DRINK -> category.setNeededTime(1);
                        case OTHER -> category.setNeededTime(5);
                    }

                    categoryRepository.save(category);
                });

    }

    @Override
    public CategoryEntity findByCategoryName(CategoryNameEnum categoryNameEnum) {
        return categoryRepository
                .findByName(categoryNameEnum)
                .orElse(null);
    }
}
