package com.example.service;

import com.example.dto.category.superCategory.CategoryGetDTO;
import com.example.dto.category.superCategory.CategoryCreationDto;
import com.example.entity.category.CategoryEntity;
import com.example.entity.category.InnerCategoryEntity;
import com.example.exception.CategoryAlreadyExistsException;
import com.example.exception.CategoryNotFoundException;
import com.example.exception.InnerCategoryNotFoundException;
import com.example.repositiry.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Alisher Odilov
 * Date: 19.01.2023
 */

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryCreationDto create(CategoryCreationDto dto) {
        Optional<CategoryEntity> byNameUz = categoryRepository.findByNameUz(dto.getNameUz());
        Optional<CategoryEntity> byNameRu = categoryRepository.findByNameRu(dto.getNameRu());
        if (byNameUz.isPresent() || byNameRu.isPresent()) {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
        categoryRepository.save(toEntity(dto));
        return dto;
    }

    public CategoryCreationDto update(CategoryCreationDto dto, Long id) {
        CategoryEntity entity = getEntityById(id);
        entity.setPhotoId(dto.getPhoto());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());
        categoryRepository.save(entity);
        return dto;
    }

    public String deleteById(Long id) {
        CategoryEntity entity = getEntityById(id);
        entity.setVisible(false);
        categoryRepository.save(entity);
        return "successfully deleted !";
    }

    public Page<CategoryGetDTO> getList(Integer page, Integer size, String language) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> pageObj = categoryRepository.findByVisibleTrue(pageable);
        List<CategoryEntity> content = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();
        List<CategoryGetDTO> dtoList = new LinkedList<>();
        content.forEach(entity -> dtoList.add(getDTO(entity, language)));
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    public Page<CategoryGetDTO> getListAll(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryEntity> pageObj = categoryRepository.findAll(pageable);
        List<CategoryEntity> content = pageObj.getContent();
        long totalElements = pageObj.getTotalElements();
        List<CategoryGetDTO> dtoList = new LinkedList<>();
        content.forEach(entity -> dtoList.add(getDtoAll(entity)));
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    private CategoryEntity toEntity(CategoryCreationDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());
        entity.setPhotoId(dto.getPhoto());
        entity.setVisible(false);
        return entity;
    }

    private CategoryEntity getEntityById(Long id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException(" Category not found ! ");
        }
        return optional.get();
    }

    private CategoryGetDTO getDTO(CategoryEntity entity, String language) {
        CategoryGetDTO dto = new CategoryGetDTO();
        dto.setPhotoId(entity.getPhotoId());
        if (language.equals("UZ")) {
            dto.setNameUz(entity.getNameUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
        }

        if (language.equals("RU")) {
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }
        return dto;
    }

    private CategoryGetDTO getDtoAll(CategoryEntity entity) {
        CategoryGetDTO dto = new CategoryGetDTO();
        dto.setPhotoId(entity.getPhotoId());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setDescriptionRu(entity.getDescriptionRu());
        return dto;
    }

    public String changeStatus(Long id) {
        CategoryEntity entity = getEntityById(id);
        entity.setVisible(true);
        categoryRepository.save(entity);
        return "Status successfully changed";
    }
}
