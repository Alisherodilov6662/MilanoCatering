package com.example.service;

import com.example.dto.category.superCategory.CategoryCreationDto;
import com.example.dto.category.superCategory.CategoryGetDTO;
import com.example.dto.category.superCategory.CategoryGetForPublish;
import com.example.entity.category.CategoryEntity;
import com.example.enums.Status;
import com.example.exception.CategoryAlreadyExistsException;
import com.example.exception.CategoryNotFoundException;
import com.example.repositiry.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Author: Alisher Odilov
 * Date: 19.01.2023
 */
@AllArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryCreationDto create(CategoryCreationDto dto) {
        Optional<CategoryEntity> byNameUz = categoryRepository.findByNameUz(dto.getNameUz());
        Optional<CategoryEntity> byNameRu = categoryRepository.findByNameRu(dto.getNameRu());
        if (byNameUz.isEmpty() && byNameRu.isEmpty()) {
            this.categoryRepository.save(toEntity(dto));
            return dto;
        } else {
            throw new CategoryAlreadyExistsException("Category already exists");
        }
    }

    public CategoryCreationDto update(CategoryCreationDto dto, Long id) {
        CategoryEntity entity = getEntityById(id);
        entity.setPhotoId(dto.getPhoto());
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());
        this.categoryRepository.save(entity);
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
        content.forEach((entity) -> dtoList.add(getDTO(entity, language)));
        return new PageImpl<>(dtoList, pageable, totalElements);
    }

    private CategoryEntity toEntity(CategoryCreationDto dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setNameRu(dto.getNameRu());
        entity.setDescriptionUz(dto.getDescriptionUz());
        entity.setDescriptionRu(dto.getDescriptionRu());
        entity.setStatus(Status.NOT_PUBLISHED);
        entity.setPhotoId(dto.getPhoto());
        entity.setVisible(true);
        return entity;
    }

    private CategoryEntity getEntityById(Long id) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new CategoryNotFoundException(" Category not found ! ");
        } else {
            return optional.get();
        }
    }

    private CategoryGetDTO getDTO(CategoryEntity entity, String language) {
        CategoryGetDTO dto = new CategoryGetDTO();
        dto.setPhotoId(entity.getPhotoId());
        if (language.equals("UZ")) {
            dto.setId(entity.getId());
            dto.setNameUz(entity.getNameUz());
            dto.setDescriptionUz(entity.getDescriptionUz());
        }

        if (language.equals("RU")) {
            dto.setId(entity.getId());
            dto.setNameRu(entity.getNameRu());
            dto.setDescriptionRu(entity.getDescriptionRu());
        }

        return dto;
    }

    public String changeStatus(Long id) {
        CategoryEntity entity = getEntityById(id);
        entity.setVisible(true);
        this.categoryRepository.save(entity);
        return "Status successfully changed";
    }

    public List<CategoryGetForPublish> getCategoryNotPublish() {
        List<CategoryEntity> list = categoryRepository.findByStatusNotPublished(Status.NOT_PUBLISHED.name(), Boolean.TRUE);
        if (list.isEmpty()) {
            return null;
        } else {
            List<CategoryGetForPublish> listDto = new ArrayList<>();
            list.forEach((entity) -> listDto.add(toDTO(entity)));
            return listDto;
        }
    }

    private CategoryGetForPublish toDTO(CategoryEntity entity) {
        CategoryGetForPublish dto = new CategoryGetForPublish();
        dto.setId(entity.getId());
        dto.setName_Uz(entity.getNameUz());
        dto.setDescription_Uz(entity.getDescriptionUz());
        return dto;
    }

    public Integer getQuantity() {
        List<CategoryEntity> entities = categoryRepository.findByStatusNotPublished(Status.NOT_PUBLISHED.name(), Boolean.TRUE);
        if (entities.isEmpty()) {
            return 0;
        } else {
            return entities.size();
        }
    }

    public CategoryCreationDto getByIdForUpdate(Long id) {
        CategoryEntity entity = getEntityById(id);
        CategoryCreationDto dto = new CategoryCreationDto();
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        dto.setDescriptionRu(entity.getDescriptionRu());
        dto.setDescriptionUz(entity.getDescriptionUz());
        dto.setPhoto(entity.getPhotoId());
        return dto;
    }
}
