package com.example.repositiry;

import com.example.entity.category.InnerCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InnerCategoryRepository extends JpaRepository<InnerCategoryEntity, Long> {
    Optional<InnerCategoryEntity> findByNameUz(String nameUz);
    Optional<InnerCategoryEntity> findByNameRu(String nameRu);
    Page<InnerCategoryEntity> findByVisibleTrue(Pageable pageable);

    Page<InnerCategoryEntity> findByCategoryId(Pageable pageable, Long id);
}