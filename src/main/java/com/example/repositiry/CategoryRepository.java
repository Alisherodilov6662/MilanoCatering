package com.example.repositiry;

import com.example.entity.category.CategoryEntity;
import com.example.entity.category.InnerCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Author: Alisher Odilov
 * Date: 19.01.2023
 */

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity,Long> {
    Optional<CategoryEntity> findByNameUz(String nameUz);

    Optional<CategoryEntity> findByNameRu(String nameRu);

    Page<CategoryEntity> findByVisibleTrue(Pageable pageable);
}
