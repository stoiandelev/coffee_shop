package com.example.coffee_shop_exam.service;


import com.example.coffee_shop_exam.model.entity.CategoryEntity;
import com.example.coffee_shop_exam.model.entity.CategoryNameEnum;

public interface CategoryService {

    void initCategory();

    CategoryEntity findByCategoryName(CategoryNameEnum categoryNameEnum);
}
