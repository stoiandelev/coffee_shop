package com.example.coffee_shop_exam.repository;

import com.example.coffee_shop_exam.model.entity.CategoryEntity;
import com.example.coffee_shop_exam.model.entity.CategoryNameEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByName(CategoryNameEnum name);

}
