package com.example.repositiry;

import com.example.entity.category.InnerCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InnerCategoryRepository extends JpaRepository<InnerCategoryEntity, Long> {
    Optional<InnerCategoryEntity> findByNameUz(String nameUz);

    Optional<InnerCategoryEntity> findByNameRu(String nameRu);

    Page<InnerCategoryEntity> findByVisibleTrue(Pageable pageable);

    @Query(value = "select * from category where status=?1 and visible=?2", nativeQuery = true)
    List<InnerCategoryEntity> findByStatusNotPublished(String name, Boolean aTrue);

    @Query(value = "select * from inner_category where category_id=?1", nativeQuery = true)
    List<InnerCategoryEntity> getByCategoryId(Long id);
}