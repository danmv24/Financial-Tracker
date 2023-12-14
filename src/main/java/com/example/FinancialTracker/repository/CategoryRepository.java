package com.example.FinancialTracker.repository;

import com.example.FinancialTracker.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCategoryNameIgnoreCase(String categoryName);
}
