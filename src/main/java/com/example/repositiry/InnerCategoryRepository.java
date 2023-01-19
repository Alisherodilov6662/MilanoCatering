package com.example.repositiry;

import com.example.entity.category.InnerCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InnerCategoryRepository extends JpaRepository<InnerCategoryEntity, Long> {

}